package undecided.demo.catalog.application;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import undecided.demo.catalog.BookAddedToCatalog;
import undecided.demo.catalog.domain.CatalogBook;
import undecided.demo.catalog.domain.CatalogRepository;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class CatalogManagement {

  private final CatalogRepository catalogRepository;
  private final BookMapper mapper;
  private final ApplicationEventPublisher events;

  /**
   * Add a new book to the library.
   */
  public BookDto addToCatalog(String title, CatalogBook.Barcode catalogNumber, String isbn,
      String authorName) {
    var book = new CatalogBook(title, catalogNumber, isbn, new CatalogBook.Author(authorName));
    var dto = mapper.toDto(catalogRepository.save(book));
    events.publishEvent(
        new BookAddedToCatalog(dto.title(), dto.catalogNumber().barcode(), dto.isbn(),
            dto.author().name()));
    return dto;
  }

  @Transactional(readOnly = true)
  public Optional<BookDto> locate(Long id) {
    return catalogRepository.findById(id)
        .map(mapper::toDto);
  }

  @Transactional(readOnly = true)
  public List<BookDto> fetchBooks() {
    return catalogRepository.findAll()
        .stream()
        .map(mapper::toDto)
        .toList();
  }
}
