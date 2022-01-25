# Command bus

A command is an object that signals an **intent**, like `SubscribeToNewsletter`.

1. Commands are passed to the command bus.
2. Command bus will pass the command to a handler.
3. The handler will perform the required action, like subscribing to a newsletter.

The following sections explain what you need to know to use and customize a command bus.

- [Creating a command](#creating-a-command)
  - [Locating Commands](#locating-commands)
  - [Instantiating Commands](#instantiating-commands)
  - [Naming convention](#naming-convention)
    - [Command handlers](#command-handlers)
    - [Commands](#commands)
- [Creating a command bus](#creating-a-command-bus)
- [Executing a command](#executing-a-command)
- [Middleware](#middleware)
  - [Middleware example](#middleware-example)

## Creating a command

Following the example above (subscribing to a newsletter), the snippet below shows how you would create said command and its corresponding command handler

```java
public final class SubscribeToNewsLetterInput implements Command {
  // Information required to subscribe: email, name, etc.
}

public final class SubscribeToNewsLetterAction 
  implements CommandHandler<SubscribeToNewsLetterInput> {
    @Override
    public void execute(SubscribeToNewsLetterInput input) {
        // Logic to subscribe to a newsletter
    }
}
```

### Locating commands

This package provides a default command locator, that uses [reflection](https://github.com/ronmamo/reflections) to match commands and command handlers

1. Looks for all command handlers (classes implementing `CommandHandler`
2. It will extract the parameterized type of the handler (its `Command` class name)
3. With the information gathered in the above 2 steps, it will map a command with its command handler

That's how this library will automatically detect and map all commands and command handlers of a given package

```java
var locator = new ReflectionsCommandHandlerLocator("commands.package");
```

### Instantiating commands

Once command handlers are located they still need to be instantiated.
This library comes with a default factory for command handlers.

```java
var factory = new InMemoryCommandHandlerFactory();
factory.add(
  SubscribeToNewsLetterAction.class,
  new SubscribeToNewsLetterAction());
```

In a Spring Boot application you would use the Bean factory instead.

### Naming convention

#### Command handlers

This library uses the suffix `Action` for both **command and query handlers**.

However, you're free to use no suffix, or use `CommandHandler` or `QueryHandler`, or any other variation.
Since services are located via reflection, class names do not matter in that regard.

You're also free to provide your own implementation for locating handlers by implementing the `CommandHandlerLocator` interface.

#### Commands

This library uses the suffix `Input` for both **commands and queries**.

All commands have a method `action()` that provides useful information that can be added to exception error messages or to log entries.

```java
public final class Action {
  // Action slugs could be used for log entries, 
  //   as a log marker or as part of the message. 
  // It could also be used as part of an HTTP response 
  //   as a code
  public String toSlug() { /* .. */ }

  // This can be part of an exception message or log entry.
  // For instance:
  // "Cancel newsletter subscription" completed in 200 milliseconds
  public String toSentence() { /* .. */ }

  // This method can also be used in as part of an existing message.
  // For instance:
  // Cannot "cancel newsletter subscription" because:
  //  exception.getMessage()  
  public String toWords() { /* .. */ }
}
```

The default implementation of `Command` assumes its name is suffixed with `Input`

```java
public interface Command extends Input {
  default Action action() {
    return Action.withoutSuffix(
      this.getClass().getSimpleName(),
      "Input");
  }
}
```

However, you can extend `Command` and provide your own suffix so that you can still take advantage of the additional information provided by the `Action` class.

```java
public interface ApplicationCommand extends Command {
  default Action action() {
    return Action.withoutSuffix(
        this.getClass().getSimpleName(), 
        "Command"); // no suffix "" or any other suffix
  }
}
```

## Creating a command bus

```java
public final class Application {
  public static void main(String[] args) {
    var factory = new InMemoryCommandHandlerFactory();
    var locator = new ReflectionsCommandHandlerLocator("commands.package");
    var handlerMiddleware = new CommandHandlerMiddleware(locator, factory);
    var bus = new MiddlewareCommandBus(List.of(handlerMiddleware));
  }
}
```

## Executing a command

```java
var command = new SubscribeToNewsLetterInput();
bus.dispatch(command);
```

## Middleware

Middleware are a way to **add behavior** to commands.
When you execute a command, it is passed through every Middleware.
Middleware are executed in sequence; the order is configured when you set up the `CommandBus` and can’t be changed later.

Middleware can control when the next middleware starts.
This allows you to control if your custom behavior will come **before** or **after** command execution, or if you’ll **suppress** the command from being executed at all.

Middleware is a very useful concept for lots of things.
You could write middleware for:

- Database transactions
- Error handling
- Queuing
- Logging
- Validation
- Permissions

### Logging Middleware

This package provides a middleware to log how much time commands take. 
See the code excerpt below that illustrates how middleware work.

```java
public final class CommandLoggerMiddleware implements CommandMiddleware<Command> {

  // ...

  @Override
  public void execute(Command command, CommandHandler<Command> next) {
    Instant start = clock.instant();
    next.execute(command);
    Instant end = clock.instant();

    feed.record(commandCompleted(command.action(), Duration.between(start, end)));
  }
}
```

The next step is to add the logger middleware to the bus

```java
public final class Application {
  public static void main(String[] args) {
    // ...
    var logger = LoggerFactory.getLogger(Application.class);
    var feed = new ActivityFeed(logger);
    var clock = Clock.systemUTC();
    var loggerMiddleware = new LoggerMiddleware(feed, clock);

    var bus = new MiddlewareCommandBus(List.of(
      loggerMiddleware,
      handlerMiddleware));
  }
}
```

#### Querying your logs

The logging events generate by the middleware explained in the previous section look as follows.

```json
{
  "identifier": "command",
  "context": {
    "command": "update-subscription",
    "durationInMilliseconds": 500
  }
}
```

With the information provided by the logging event described above, you'll be able to: 

- Query your logs per command
- Look for slow commands

Below is the query to look for the command `update-subscription` in AWS Cloudwatch.

```sql
fields @timestamp, message, `context.durationInMilliseconds`
| filter `context.command` = "update-subscription"
| sort @timestamp desc
| limit 50
```
