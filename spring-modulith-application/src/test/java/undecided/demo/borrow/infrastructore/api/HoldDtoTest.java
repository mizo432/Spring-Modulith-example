package undecided.demo.borrow.infrastructore.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import undecided.demo.borrow.model.Book;
import undecided.demo.borrow.model.Hold;
import undecided.demo.borrow.model.Patron;

class HoldDtoTest {

  /**
   * Method under test: {@link HoldDto#from(Hold)}
   */
  @Test
  void from() {
    // Arrange
    Hold hold = mock(Hold.class);
    when(hold.getDateOfHold()).thenReturn(LocalDate.of(1970, 1, 1));
    when(hold.getHeldBy()).thenReturn(new Patron.PatronId(UUID.randomUUID()));
    when(hold.getOnBook()).thenReturn(new Book.Barcode("Barcode"));
    when(hold.getId()).thenReturn(new Hold.HoldId(UUID.randomUUID()));

    // Act
    HoldDto actualFromResult = HoldDto.from(hold);

    // Assert
    verify(hold).getDateOfHold();
    verify(hold).getHeldBy();
    verify(hold).getId();
    verify(hold).getOnBook();
    assertThat(actualFromResult.getDateOfHold().toString()).isEqualTo("1970-01-01");
    assertThat(actualFromResult.getBookBarcode()).isEqualTo("Barcode");
  }
}
