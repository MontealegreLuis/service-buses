# Query bus

A query is an object that signals an **intent**, like `SearchProducts`.

1. Queries are passed to the query bus.
2. Query bus will pass the query to a handler.
3. The handler will perform the required action, like searching products.

The following sections explain what you need to know to use and customize a query bus.

- [Creating a query](#creating-a-query)
    - [Locating Queries](#locating-queries)
    - [Instantiating Queries](#instantiating-queries)
    - [Naming convention](#naming-convention)
        - [Query handlers](#query-handlers)
        - [Queries](#queries)
- [Creating a query bus](#creating-a-query-bus)
- [Executing a query](#executing-a-query)
- [Middleware](#middleware)

## Creating a query

Following the example above (searching products), the snippet below shows how you would create said query and its corresponding query handler.

```java
public final class SearchProductsInput implements Query {
  // Information required to search products: 
  // department, price range, etc.
}

public final class SearchProductsResult implements Response {
  // Response returned by the query:
  // matching products, page number, total count, etc.
}

@Query
public final class SearchProductsAction 
  implements QueryHandler<SearchProductsInput, SearchProductsResult> {
    @Override
    public SearchProductsResult execute(SearchProductsInput input) {
      // Logic to search for products
    }
}
```

As shown in the snippet above, this package provides a `@Query` annotation, so you can use Spring Boot [component scanning](https://www.baeldung.com/spring-componentscan-filter-type#annotation_filter) in case your queries are in a separate package.

### Locating queries

This package provides a default query locator, that uses [reflection](https://github.com/ronmamo/reflections) to match queries and queries handlers

1. Looks for all query handlers (classes implementing `QueryHandler`
2. It will extract the parameterized type of the handler (its `Query` class name)
3. With the information gathered in the above 2 steps, it will map a query with its query handler

That's how this library will automatically detect and map all queries and query handlers of a given package

```java
var locator = new ReflectionsQueryHandlerLocator("queries.package");
```

### Instantiating queries

Once query handlers are located they still need to be instantiated.
This library comes with a default factory for query handlers.

```java
var factory = new InMemoryQueryHandlerFactory();
factory.add(
  SearchProductsAction.class,
  new SearchProductsAction());
```

In a Spring Boot application you would use the `ApplicationContextQueryHandlerFactory` instead.

### Naming convention

#### Query handlers

This library uses the suffix `Action` for both **command and query handlers**.

However, you're free to use no suffix, or use `QueryHandler`, or any other variation.
Since services are located via reflection, class names do not matter in that regard.

You're also free to provide your own implementation for locating handlers by implementing the `QueryHandlerLocator` interface.

#### Queries

This library uses the suffix `Input` for both **commands and queries**.

All queries have a method `action()` that provides useful information that can be added to exception error messages or to log entries.

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

The default implementation of `Query` assumes its name is suffixed with `Input`

```java
public interface Query extends Input {
  default Action action() {
    return Action.withoutSuffix(
      this.getClass().getSimpleName(),
      "Input");
  }
}
```

However, you can extend `Query` and provide your own suffix so that you can still take advantage of the additional information provided by the `Action` class.

```java
public interface ApplicationQuery extends Query {
  default Action action() {
    return Action.withoutSuffix(
        this.getClass().getSimpleName(), 
        "Query"); // or no suffix "", or any other suffix
  }
}
```

## Creating a query bus

```java
public final class Application {
  public static void main(String[] args) {
    var factory = new InMemoryQueryHandlerFactory();
    var locator = new ReflectionsQueryHandlerLocator("queries.package");
    var bus = new QueryBusDispatch(locator, factory);
  }
}
```

## Executing a query

```java
var query = new SearchProductsInput();
var response = bus.dispatch(query);
```

## Middleware

Middleware are a way to **add behavior** to queries.
It's a query bus plugin mechanism.

Middleware is a very useful concept for things like:

- Logging
- Error handling
- Validation
- Permissions

Please check [this package](https://github.com/MontealegreLuis/service-buses-middleware) for logging and error handler middleware.
