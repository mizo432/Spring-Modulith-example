package undecided.demo.catalog.application;

import undecided.demo.catalog.domain.CatalogBook;

public record BookDto(Long id, String title, CatalogBook.Barcode catalogNumber,
                      String isbn, CatalogBook.Author author) {
  
}
