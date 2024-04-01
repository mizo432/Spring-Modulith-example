package undecided.demo.catalog.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class CatalogBookTest {

  /**
   * Method under test:
   * {@link CatalogBook#CatalogBook(String, CatalogBook.Barcode, String, CatalogBook.Author)}
   */
  @Test
  void constructor() {
    // Arrange
    CatalogBook.Barcode catalogNumber = new CatalogBook.Barcode("Barcode");
    CatalogBook.Author author = new CatalogBook.Author("Name");

    // Act
    CatalogBook actualCatalogBook = new CatalogBook("Dr", catalogNumber, "Isbn", author);

    // Assert
    CatalogBook.Barcode catalogNumber2 = actualCatalogBook.getCatalogNumber();
    assertThat(catalogNumber2.barcode()).isEqualTo("Barcode");
    assertThat(actualCatalogBook.getTitle()).isEqualTo("Dr");
    assertThat(actualCatalogBook.getIsbn()).isEqualTo("Isbn");
    CatalogBook.Author author2 = actualCatalogBook.getAuthor();
    assertThat(author2.name()).isEqualTo("Name");
    assertThat(actualCatalogBook.getId()).isNull();
    assertThat(actualCatalogBook.getVersion()).isNull();
    assertThat(author2).isSameAs(author);
    assertThat(catalogNumber2).isSameAs(catalogNumber);
  }
}
