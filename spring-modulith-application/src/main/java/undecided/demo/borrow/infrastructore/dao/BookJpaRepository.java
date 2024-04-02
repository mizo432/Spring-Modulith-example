package undecided.demo.borrow.infrastructore.dao;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import undecided.demo.borrow.domain.Book;


public interface BookJpaRepository extends JpaRepository<BookEntity, UUID> {

  Optional<BookEntity> findByInventoryNumber(Book.Barcode inventoryNumber);

  Optional<BookEntity> findByInventoryNumberAndStatus(Book.Barcode inventoryNumber,
      Book.BookStatus status);
}
