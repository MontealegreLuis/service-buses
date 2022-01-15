# Service buses

[![CI workflow](https://github.com/montealegreluis/service-buses/actions/workflows/ci.yml/badge.svg)](https://github.com/montealegreluis/service-buses/actions/workflows/ci.yml)

This Maven package provides buses for commands and queries as defined by CQRS (Command-Query Responsibility Segregation)

In CQRS, services are split into two separate types, a read-side and a write-side (**Command** and **Query**). Commands change information and Queries return information.

## Command bus

A command is an object that signals an intent, like `SubscribeToNewsleter`. 

1. Commands are passed to the command bus.
2. Command bus will pass the command to a handler.
3. The handler will perform the required action, like subscribing to newsletter.

### Creating a command

Following the example above this is how we'd create said command and its corresponding command handler

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

### Creating a command bus

```java
public final class Application {
  public static void main(String[] args) {
    var factory = new InMemoryCommandHandlerFactory();
    var locator = new CommandHandlersLocator("commands.handlers.package");
    var handlerMiddleware = new CommandHandlerMiddleware(locator, factory);
    var bus = new MiddlewareCommandBus(List.of(handlerMiddleware));
  }
}
```

### Executing a command

```java
var command = new SubscribeToNewsLetterInput();
bus.dispatch(command);
```

### Middleware

Middleware are a way to add behavior, like writing to a log, every time you execute a command.

When you execute a command, it is passed through every Middleware. 

Middleware are executed in sequence; the order is configured when you set up the CommandBus and can’t be changed later. 

Middleware can control when the next link in the chain starts. 
This allows you to control if your custom behavior will come **before** or **after** command execution, or if you’ll **suppress** the command from being executed at all.

#### Middleware example

The example below logs when a command starts and when it ends.

```java
public final class LoggerMiddleware implements CommandMiddleware<Command> {
  private final Logger logger;
  
  public CommandMiddleware(Logger logger) {
    this.logger = logger;
  }

  public void execute(Command command, CommandHandler<Command> next) {
    var commandName = command.action().toSentence();
    
    logger.log(commandName + " has started");
    
    next.execute(command);
    
    logger.log(commandName + " has completed");
  }
}
```

Next step is to add your new middleware to the bus

```java
public final class Application {
  public static void main(String[] args) {
    // ...
    var logger = LoggerFactory.getLogger(Application.class);
    var loggerMiddleware = new LoggerMiddleware(logger);
    
    var bus = new MiddlewareCommandBus(List.of(
      loggerMiddleware, 
      handlerMiddleware));
  }
}
```

## Installation

1. [Authenticating to GitHub Packages](https://github.com/MontealegreLuis/activity-feed/blob/main/docs/installation/authentication.md)
2. [Maven](https://github.com/MontealegreLuis/activity-feed/blob/main/docs/installation/maven.md)
3. [Gradle](https://github.com/MontealegreLuis/activity-feed/blob/main/docs/installation/gradle.md)

## Contribute

Please refer to [CONTRIBUTING](https://github.com/MontealegreLuis/service-buses/blob/main/CONTRIBUTING.md) for information on how to contribute to Service Buses.

## License

Released under the [BSD-3-Clause](https://github.com/MontealegreLuis/service-buses/blob/main/LICENSE).
