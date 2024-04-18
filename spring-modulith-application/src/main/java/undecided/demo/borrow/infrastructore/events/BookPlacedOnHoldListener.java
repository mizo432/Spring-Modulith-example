package undecided.demo.borrow.infrastructore.events;

import lombok.RequiredArgsConstructor;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;
import undecided.demo.borrow.application.CirculationDeskCommand;
import undecided.demo.borrow.model.Book;
import undecided.demo.borrow.model.BookPlacedOnHold;

@Component
@RequiredArgsConstructor
public class BookPlacedOnHoldListener {

  private final CirculationDeskCommand circulationDeskCommand;

  @ApplicationModuleListener
  public void handle(BookPlacedOnHold event) {
    circulationDeskCommand.holdBook(new Book.Barcode(event.inventoryNumber()));

  }

}
