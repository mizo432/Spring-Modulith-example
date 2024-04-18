package undecided.demo.borrow.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import undecided.demo.borrow.model.Book;
import undecided.demo.borrow.model.Book.AddBook;
import undecided.demo.borrow.model.Book.Barcode;
import undecided.demo.borrow.model.BookRepository;
import undecided.demo.borrow.model.Hold;
import undecided.demo.borrow.model.HoldEventPublisher;
import undecided.demo.borrow.model.HoldRepository;
import undecided.shared.constants.constants.MyConstants;

@Service
@Transactional
@Slf4j
public class CirculationDeskCommand {

  private final BookRepository bookRepository;
  private final HoldRepository holdRepository;
  private final HoldEventPublisher eventPublisher;

  public CirculationDeskCommand(BookRepository bookRepository, HoldRepository holdRepository,
      HoldEventPublisher eventPublisher) {
    this.bookRepository = bookRepository;
    this.holdRepository = holdRepository;
    this.eventPublisher = eventPublisher;
  }

  public Hold createAndPublishHold(Hold.PlaceHold command) {
    bookRepository.findAvailableBook(command.inventoryNumber())
        .orElseThrow(() -> new IllegalArgumentException("Book not found"));
    log.info(MyConstants.DUMMY_STRING);
    return saveAndPublishHold(Hold.placeHold(command));
  }

  private Hold saveAndPublishHold(Hold hold) {
    return hold
        .then(holdRepository::save)
        .then(eventPublisher::holdPlaced);
  }

  public void addBook(AddBook command) {
    bookRepository.save(Book.addBook(command));
  }

  public void holdBook(Barcode barcode) {
    bookRepository.findAvailableBook(barcode)
        .map(Book::markOnHold)
        .map(bookRepository::save)
        .orElseThrow(() -> new IllegalArgumentException("Duplicate hold?"));
  }
}