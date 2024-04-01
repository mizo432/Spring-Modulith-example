package undecided.demo.borrow.domain;

import java.util.Optional;
import org.jmolecules.ddd.annotation.Repository;

@Repository
public interface BookRepository {

  Optional<Book> findAvailableBook(Book.Barcode inventoryNumber);

  Optional<Book> findOnHoldBook(Book.Barcode inventoryNumber);

  Book save(Book book);

  Optional<Book> findByBarcode(String barcode);
}
