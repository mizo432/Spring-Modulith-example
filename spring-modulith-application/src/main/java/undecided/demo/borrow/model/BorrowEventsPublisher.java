package undecided.demo.borrow.model;


import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BorrowEventsPublisher implements HoldEventPublisher {

  private final ApplicationEventPublisher publisher;

  @Override
  public void holdPlaced(BookPlacedOnHold event) {
    publisher.publishEvent(event);
  }
}
