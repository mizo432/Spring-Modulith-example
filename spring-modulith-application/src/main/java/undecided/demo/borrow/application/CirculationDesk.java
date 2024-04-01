package undecided.demo.borrow.application;

import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import undecided.demo.borrow.domain.Book;
import undecided.demo.borrow.domain.Book.AddBook;
import undecided.demo.borrow.domain.Book.Barcode;
import undecided.demo.borrow.domain.BookRepository;
import undecided.demo.borrow.domain.Hold;
import undecided.demo.borrow.domain.HoldEventPublisher;
import undecided.demo.borrow.domain.HoldRepository;

/**
 * The CirculationDesk class represents a circulation desk in a library. It handles activities
 * related to placing holds, adding books, holding books, and locating holds.
 */
@Service
@Transactional
public class CirculationDesk {

  private final BookRepository books;
  private final HoldRepository holds;
  private final HoldEventPublisher eventPublisher;

  public CirculationDesk(BookRepository books, HoldRepository holds,
      HoldEventPublisher eventPublisher) {
    this.books = books;
    this.holds = holds;
    this.eventPublisher = eventPublisher;
  }

  public HoldDto placeHold(Hold.PlaceHold command) {
    books.findAvailableBook(command.inventoryNumber())
        .orElseThrow(() -> new IllegalArgumentException("Book not found"));

    return HoldDto.from(
        Hold.placeHold(command)
            .then(holds::save)
            .then(eventPublisher::holdPlaced)
    );
  }

  public Optional<HoldDto> locate(UUID holdId) {
    return holds.findById(new Hold.HoldId(holdId))
        .map(HoldDto::from);
  }

  public void addBook(AddBook command) {
    books.save(Book.addBook(command));

  }

  public void holdBook(Barcode barcode) {
    books.findAvailableBook(barcode)
        .map(Book::markOnHold)
        .map(books::save)
        .orElseThrow(() -> new IllegalArgumentException("Duplicate hold?"));

  }
}