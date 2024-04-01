package undecided.demo.borrow.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class BookTest {

  /**
   * Method under test:
   * {@link Book#toBook(Book.BookId, Book.Barcode, String, String, Book.BookStatus)}
   */
  @Test
  void toBook() {
    // Arrange
    Book.BookId id = new Book.BookId(UUID.randomUUID());

    // Act
    Book actualToBookResult = Book.toBook(id, new Book.Barcode("Barcode"), "Dr", "Isbn",
        Book.BookStatus.AVAILABLE);

    // Assert
    assertEquals("Barcode", actualToBookResult.getInventoryNumber().barcode());
    assertEquals("Dr", actualToBookResult.getTitle());
    assertEquals("Isbn", actualToBookResult.getIsbn());
    assertEquals(2, actualToBookResult.getId().id().variant());
    assertEquals(Book.BookStatus.AVAILABLE, actualToBookResult.getStatus());
  }

  /**
   * Method under test:
   * {@link Book#toBook(Book.BookId, Book.Barcode, String, String, Book.BookStatus)}
   */
  @Test
  void toBook2() {
    // Arrange
    Book.BookId id = new Book.BookId(UUID.randomUUID());

    // Act
    Book actualToBookResult = Book.toBook(id, new Book.Barcode("Barcode"), "Dr", "Isbn",
        Book.BookStatus.ON_HOLD);

    // Assert
    assertEquals("Barcode", actualToBookResult.getInventoryNumber().barcode());
    assertEquals("Dr", actualToBookResult.getTitle());
    assertEquals("Isbn", actualToBookResult.getIsbn());
    assertEquals(2, actualToBookResult.getId().id().variant());
    assertEquals(Book.BookStatus.ON_HOLD, actualToBookResult.getStatus());
  }

  /**
   * Method under test:
   * {@link Book#toBook(Book.BookId, Book.Barcode, String, String, Book.BookStatus)}
   */
  @Test
  void toBook3() {
    // Arrange
    Book.BookId id = new Book.BookId(UUID.randomUUID());

    // Act
    Book actualToBookResult = Book.toBook(id, new Book.Barcode("Barcode"), "Dr", "Isbn",
        Book.BookStatus.ISSUED);

    // Assert
    assertEquals("Barcode", actualToBookResult.getInventoryNumber().barcode());
    assertEquals("Dr", actualToBookResult.getTitle());
    assertEquals("Isbn", actualToBookResult.getIsbn());
    assertEquals(2, actualToBookResult.getId().id().variant());
    assertEquals(Book.BookStatus.ISSUED, actualToBookResult.getStatus());
  }

  /**
   * Method under test: {@link Book#addBook(Book.AddBook)}
   */
  @Test
  void addBook() {
    // Arrange and Act
    Book actualAddBookResult = Book.addBook(
        new Book.AddBook(new Book.Barcode("Barcode"), "Dr", "Isbn"));

    // Assert
    assertEquals("Barcode", actualAddBookResult.getInventoryNumber().barcode());
    assertEquals("Dr", actualAddBookResult.getTitle());
    assertEquals("Isbn", actualAddBookResult.getIsbn());
    assertEquals(2, actualAddBookResult.getId().id().variant());
    assertEquals(Book.BookStatus.AVAILABLE, actualAddBookResult.getStatus());
  }

  /**
   * Method under test: {@link Book#markOnHold()}
   */
  @Test
  void markOnHold() {
    // Arrange
    Book addBookResult = Book.addBook(new Book.AddBook(new Book.Barcode("Barcode"), "Dr", "Isbn"));

    // Act and Assert
    assertSame(addBookResult, addBookResult.markOnHold());
  }
}
