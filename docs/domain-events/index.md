# Domain events

An Event is the result of executing a command operation on an **Aggregate**, the name is usually derived from the command that was executed.
The command is the cause of the event, the event’s name is stated in terms of the command having occurred in the past.

An aggregate is the result of clustering entities and value objects into a carefully crafted consistency boundary.

Following the example in the command bus section, let's suppose we have a "subscribe to newsletter" command.

- Command: `SubscribeToNewsLetter`
- Aggregate: `NewsLetter#addSubscriber(Customer customer)`
- Event: `CustomerHasSubscribedToNewsletter`

In this example we want to record that a customer has subscribed to a company's newsletter.
The domain event implementations could be as follows.

```java
public final class CustomerHasSubscribedToNewsletter 
    extends DomainEvent {
  private final String newsLetterId;
  private final String customerId;
  
  public CustomerHasSubscribedToNewsletter(
    final NewsLetterId newsLetterId,
    final CustomerId customerId
  ) {
    super();
    this.newsLetterId = newsLetterId.value();
    this.customerId = customerId.value();
  }

  @Override
  public String aggregateId() {
    return newsLetterId;
  }
}
```

The newsletter aggregate could record said event as follows.

```java
public final class NewsLetter extends AggregateRoot {
  private final NewsLetterId newsLetterId;
  
  public void addSubscriber(Customer customer) {
    subscriptions.add(customer);
    recordThat(new CustomerHasSubscribedToNewsletter(
      newsLetterId,
      customer.id()
    ));
  }
  
  @Override
  public Identifier id() {
    return newsLetterId;
  }
}
```

One rule of Aggregates states that only a single instance should be modified in a single transaction (command), and all other dependent changes must occur in separate transactions.

Events are meant to be delivered to interested parties, in either local or foreign systems, because of said rule in aggregates, they are generally used to facilitate eventual consistency.

Decoupling this way helps provide a highly scalable and peak-performing set of cooperating services. 
It also allows us to achieve loose coupling between systems.

Events are delivered to interested parties by an `EventBus` as shown in the example below.

```java
public final class SubscribeToNewsLetterAction
    implements CommandHandler<SubscribeToNewsLetterInput> {
  private final EventBus eventBus;
  
  public void execute(SubscribeToNewsLetterInput command)
      throws ActionException {
    // ...
    // Aggregate will record a domain event
    newsLetter.addSubscriber(customer);
    // ...
    // Event bus will dispatch all recorded events
    eventBus.dispatch(newsLetter.events());
  }
}
```
