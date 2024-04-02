package undecided.demo.borrow.infrastructore.events;

import lombok.RequiredArgsConstructor;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;
import undecided.demo.borrow.application.CirculationDeskCommand;
import undecided.demo.borrow.domain.Book.AddBook;
import undecided.demo.borrow.domain.Book.Barcode;
import undecided.demo.catalog.BookAddedToCatalog;

@Component
@RequiredArgsConstructor
public class BookAddedToCatalogListener {

  private final CirculationDeskCommand circulationDeskCommand;

  @ApplicationModuleListener
  public void handle(BookAddedToCatalog event) {
    var command = new AddBook(new Barcode(event.inventoryNumber()),
        event.title(),
        event.isbn());
    circulationDeskCommand.addBook(command);
  }

}
