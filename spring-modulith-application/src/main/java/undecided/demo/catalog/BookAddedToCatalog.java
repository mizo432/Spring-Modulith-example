package undecided.demo.catalog;

import org.jmolecules.event.annotation.DomainEvent;

/**
 * The BookAddedToCatalog class represents a domain event that occurs when a book is added to the
 * catalog.
 */
@DomainEvent
public record BookAddedToCatalog(String title, String inventoryNumber,
                                 String isbn, String author) {

}
