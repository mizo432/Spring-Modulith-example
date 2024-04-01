package undecided.demo.borrow.infrastructore.events;

import lombok.RequiredArgsConstructor;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;
import undecided.demo.borrow.application.CirculationDesk;
import undecided.demo.borrow.domain.Book;
import undecided.demo.borrow.domain.BookPlacedOnHold;

@Component
@RequiredArgsConstructor
public class BookPlacedOnHoldListener {

  private final CirculationDesk circulationDesk;

  @ApplicationModuleListener
  public void handle(BookPlacedOnHold event) {
    circulationDesk.holdBook(new Book.Barcode(event.inventoryNumber()));

  }

}
