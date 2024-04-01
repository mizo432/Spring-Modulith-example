package undecided.demo.borrow.application;

import java.util.Optional;
import java.util.UUID;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import undecided.demo.borrow.domain.Book;
import undecided.demo.borrow.domain.BookPlacedOnHold;
import undecided.demo.borrow.domain.BookRepository;
import undecided.demo.borrow.domain.Hold;
import undecided.demo.borrow.domain.HoldEventPublisher;
import undecided.demo.borrow.domain.HoldRepository;
import undecided.demo.catalog.BookAddedToCatalog;

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

  @ApplicationModuleListener
  public void handle(BookPlacedOnHold event) {
    books.findAvailableBook(new Book.Barcode(event.inventoryNumber()))
        .map(Book::markOnHold)
        .map(books::save)
        .orElseThrow(() -> new IllegalArgumentException("Duplicate hold?"));
  }

  @ApplicationModuleListener
  public void handle(BookAddedToCatalog event) {
    var command = new Book.AddBook(new Book.Barcode(event.inventoryNumber()), event.title(),
        event.isbn());
    books.save(Book.addBook(command));
  }

}