package undecided.demo.borrow.domain;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationEventPublisher;

class BorrowEventsPublisherTest {

  /**
   * Method under test: {@link BorrowEventsPublisher#holdPlaced(BookPlacedOnHold)}
   */
  @Test
  void holdPlaced() {
    //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

    // Arrange
    ApplicationEventPublisher publisher = mock(ApplicationEventPublisher.class);
    doNothing().when(publisher).publishEvent(Mockito.<Object>any());
    BorrowEventsPublisher borrowEventsPublisher = new BorrowEventsPublisher(publisher);
    UUID holdId = UUID.randomUUID();

    // Act
    borrowEventsPublisher.holdPlaced(new BookPlacedOnHold(holdId, "42", LocalDate.of(1970, 1, 1)));

    // Assert that nothing has changed
    verify(publisher).publishEvent(isA(Object.class));
  }
}
