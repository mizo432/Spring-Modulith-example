package undecided.demo.borrow.application;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import undecided.demo.borrow.domain.Book;
import undecided.demo.borrow.domain.BookRepository;
import undecided.demo.borrow.domain.Hold;
import undecided.demo.borrow.domain.HoldEventPublisher;
import undecided.demo.borrow.domain.HoldRepository;
import undecided.demo.borrow.domain.Patron;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class CirculationDeskCommandTest {

  @MockBean
  private BookRepository bookRepository;

  @Autowired
  private CirculationDeskCommand circulationDeskCommand;

  @MockBean
  private HoldEventPublisher holdEventPublisher;

  @MockBean
  private HoldRepository holdRepository;

  /**
   * Method under test: {@link CirculationDeskCommand#createAndPublishHold(Hold.PlaceHold)}
   */
  @Test
  void createAndPublishHold() {
    // Arrange
    Book.Barcode inventoryNumber = new Book.Barcode("Barcode");
    LocalDate dateOfHold = LocalDate.of(1970, 1, 1);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> circulationDeskCommand
        .createAndPublishHold(new Hold.PlaceHold(inventoryNumber, dateOfHold,
            new Patron.PatronId(UUID.randomUUID()))));
  }

  /**
   * Method under test: {@link CirculationDeskCommand#createAndPublishHold(Hold.PlaceHold)}
   */
  @Test
  void createAndPublishHold2() {
    // Arrange
    Book.Barcode inventoryNumber = new Book.Barcode("42");
    LocalDate dateOfHold = LocalDate.of(1970, 1, 1);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> circulationDeskCommand
        .createAndPublishHold(new Hold.PlaceHold(inventoryNumber, dateOfHold,
            new Patron.PatronId(UUID.randomUUID()))));
  }

  /**
   * Method under test: {@link CirculationDeskCommand#createAndPublishHold(Hold.PlaceHold)}
   */
  @Test
  void createAndPublishHold3() {
    // Arrange
    Book.Barcode inventoryNumber = new Book.Barcode("Barcode");
    LocalDate dateOfHold = LocalDate.now();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> circulationDeskCommand
        .createAndPublishHold(new Hold.PlaceHold(inventoryNumber, dateOfHold,
            new Patron.PatronId(UUID.randomUUID()))));
  }

  /**
   * Method under test: {@link CirculationDeskCommand#addBook(Book.AddBook)}
   */
  @Test
  void addBook() {
    // Arrange
    when(bookRepository.save(Mockito.<Book>any())).thenReturn(null);

    // Act
    circulationDeskCommand.addBook(new Book.AddBook(new Book.Barcode("Barcode"), "Dr", "Isbn"));

    // Assert
    verify(bookRepository).save(isA(Book.class));
  }

  /**
   * Method under test: {@link CirculationDeskCommand#addBook(Book.AddBook)}
   */
  @Test
  void addBook2() {
    // Arrange
    when(bookRepository.save(Mockito.<Book>any())).thenThrow(new IllegalArgumentException("foo"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> circulationDeskCommand.addBook(
            new Book.AddBook(new Book.Barcode("Barcode"), "Dr", "Isbn")));
    verify(bookRepository).save(isA(Book.class));
  }

  /**
   * Method under test: {@link CirculationDeskCommand#holdBook(Book.Barcode)}
   */
  @Test
  void holdBook() {
    // Arrange
    Book book = mock(Book.class);
    when(book.markOnHold()).thenReturn(null);
    Optional<Book> ofResult = Optional.of(book);
    when(bookRepository.findAvailableBook(Mockito.<Book.Barcode>any())).thenReturn(ofResult);

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> circulationDeskCommand.holdBook(new Book.Barcode("Barcode")));
    verify(book).markOnHold();
    verify(bookRepository).findAvailableBook(isA(Book.Barcode.class));
  }
}
