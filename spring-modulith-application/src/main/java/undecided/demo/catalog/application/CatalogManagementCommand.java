package undecided.demo.catalog.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import undecided.demo.catalog.BookAddedToCatalog;
import undecided.demo.catalog.model.CatalogBook;
import undecided.demo.catalog.model.CatalogRepository;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class CatalogManagementCommand {

  private final CatalogRepository catalogRepository;
  private final ApplicationEventPublisher events;

  /**
   * Add a new book to the library.
   */
  public CatalogBook addToCatalog(String title, CatalogBook.Barcode catalogNumber, String isbn,
      String authorName) {
    var book = new CatalogBook(title, catalogNumber, isbn, new CatalogBook.Author(authorName));
    CatalogBook insertedBook = catalogRepository.save(book);
    events.publishEvent(
        new BookAddedToCatalog(insertedBook.getTitle(), insertedBook.getCatalogNumber().barcode(),
            insertedBook.getIsbn(),
            insertedBook.getAuthor().name()));
    return insertedBook;
  }

}
