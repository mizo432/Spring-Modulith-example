package undecided.demo.catalog.application;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import undecided.demo.catalog.infrastructure.api.BookDto;
import undecided.demo.catalog.model.CatalogBook;

class BookDtoTest {

  /**
   * Method under test: {@link BookDto#fromEntity(CatalogBook)}
   */
  @Test
  void fromEntity() {
    // Arrange and Act
    BookDto actualFromEntityResult = BookDto.fromEntity(new CatalogBook());

    // Assert
    assertNull(actualFromEntityResult.id());
    assertNull(actualFromEntityResult.isbn());
    assertNull(actualFromEntityResult.title());
    assertNull(actualFromEntityResult.author());
    assertNull(actualFromEntityResult.catalogNumber());
  }

  /**
   * Method under test: {@link BookDto#fromEntity(CatalogBook)}
   */
  @Test
  void fromEntity2() {
    // Arrange, Act and Assert
    assertNull(BookDto.fromEntity(null));
  }
}
