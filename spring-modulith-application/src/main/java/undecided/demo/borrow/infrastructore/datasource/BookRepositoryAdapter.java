package undecided.demo.borrow.infrastructore.datasource;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import undecided.demo.borrow.infrastructore.dao.BookEntity;
import undecided.demo.borrow.infrastructore.dao.BookJpaRepository;
import undecided.demo.borrow.model.Book;
import undecided.demo.borrow.model.BookRepository;

@Component
@Transactional
@RequiredArgsConstructor
public class BookRepositoryAdapter implements BookRepository {

  private final BookJpaRepository books;

  @Override
  public Optional<Book> findAvailableBook(Book.Barcode inventoryNumber) {
    return books.findByInventoryNumberAndStatus(inventoryNumber, Book.BookStatus.AVAILABLE)
        .map(BookEntity::toDomain);
  }

  @Override
  public Optional<Book> findOnHoldBook(Book.Barcode inventoryNumber) {
    return books.findByInventoryNumberAndStatus(inventoryNumber, Book.BookStatus.ON_HOLD)
        .map(BookEntity::toDomain);
  }

  @Override
  public Book save(Book book) {
    books.save(BookEntity.fromDomain(book));
    return book;
  }

  @Override
  public Optional<Book> findByBarcode(String barcode) {
    return books.findByInventoryNumber(new Book.Barcode(barcode))
        .map(BookEntity::toDomain);
  }
}