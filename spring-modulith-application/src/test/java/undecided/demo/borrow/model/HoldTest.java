package undecided.demo.borrow.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.UUID;
import java.util.function.UnaryOperator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class HoldTest {

  /**
   * Method under test: {@link Hold#placeHold(Hold.PlaceHold)}
   */
  @Test
  void placeHold() {
    // Arrange
    Book.Barcode inventoryNumber = new Book.Barcode("Barcode");
    LocalDate dateOfHold = LocalDate.of(1970, 1, 1);

    // Act
    Hold actualPlaceHoldResult = Hold
        .placeHold(new Hold.PlaceHold(inventoryNumber, dateOfHold,
            new Patron.PatronId(UUID.randomUUID())));

    // Assert
    assertEquals("1970-01-01", actualPlaceHoldResult.getDateOfHold().toString());
    assertEquals("Barcode", actualPlaceHoldResult.getOnBook().barcode());
    assertEquals(2, actualPlaceHoldResult.getId().id().variant());
    assertEquals(2, actualPlaceHoldResult.getHeldBy().id().variant());
  }

  /**
   * Method under test: {@link Hold#then(UnaryOperator)}
   */
  @Test
  void then() {
    // Arrange
    Book.Barcode inventoryNumber = new Book.Barcode("Barcode");
    LocalDate dateOfHold = LocalDate.of(1970, 1, 1);
    Hold placeHoldResult = Hold
        .placeHold(new Hold.PlaceHold(inventoryNumber, dateOfHold,
            new Patron.PatronId(UUID.randomUUID())));
    UnaryOperator<Hold> function = mock(UnaryOperator.class);
    Book.Barcode inventoryNumber2 = new Book.Barcode("Barcode");
    LocalDate dateOfHold2 = LocalDate.of(1970, 1, 1);
    Hold placeHoldResult2 = Hold
        .placeHold(new Hold.PlaceHold(inventoryNumber2, dateOfHold2,
            new Patron.PatronId(UUID.randomUUID())));
    when(function.apply(Mockito.<Hold>any())).thenReturn(placeHoldResult2);

    // Act
    Hold actualThenResult = placeHoldResult.then(function);

    // Assert
    verify(function).apply(isA(Hold.class));
    assertSame(placeHoldResult2, actualThenResult);
  }
}
