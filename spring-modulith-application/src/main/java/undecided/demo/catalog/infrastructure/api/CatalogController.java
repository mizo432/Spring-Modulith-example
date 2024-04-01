package undecided.demo.catalog.infrastructure.api;


import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import undecided.demo.catalog.application.BookDto;
import undecided.demo.catalog.application.CatalogManagement;
import undecided.demo.catalog.domain.CatalogBook.Barcode;

/**
 * The CatalogController class is responsible for handling HTTP requests related to the book
 * catalog.
 */
@RestController
@RequiredArgsConstructor
class CatalogController {

  private final CatalogManagement books;

  /**
   * Adds a new book to the inventory.
   *
   * @param request the request containing the book details
   * @return the ResponseEntity containing the book DTO
   */
  @PostMapping("/catalog/books")
  ResponseEntity<BookDto> addBookToInventory(@RequestBody AddBookRequest request) {
    var bookDto = books.addToCatalog(request.title(), new Barcode(request.catalogNumber()),
        request.isbn(), request.author());
    return ResponseEntity.ok(bookDto);
  }

  /**
   * Retrieves a single book from the catalog based on its ID.
   *
   * @param id the ID of the book
   * @return the ResponseEntity containing the book DTO if found, otherwise
   * ResponseEntity.notFound()
   */
  @GetMapping("/catalog/books/{id}")
  ResponseEntity<BookDto> viewSingleBook(@PathVariable("id") Long id) {
    return books.locate(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  /**
   * Retrieves the list of books from the book catalog.
   *
   * @return the ResponseEntity containing the list of book DTOs
   */
  @GetMapping("/catalog/books")
  ResponseEntity<List<BookDto>> viewBooks() {
    return ResponseEntity.ok(books.fetchBooks());
  }

  record AddBookRequest(String title, String catalogNumber,
                        String isbn, String author) {

  }
}
