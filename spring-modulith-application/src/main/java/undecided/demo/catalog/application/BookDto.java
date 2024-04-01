package undecided.demo.catalog.application;

import org.mapstruct.factory.Mappers;
import undecided.demo.catalog.domain.CatalogBook;

public record BookDto(Long id, String title, CatalogBook.Barcode catalogNumber,
                      String isbn, CatalogBook.Author author) {

  public static BookDto fromEntity(CatalogBook entity) {
    return Mappers.getMapper(BookMapper.class).toDto(entity);

  }
}
