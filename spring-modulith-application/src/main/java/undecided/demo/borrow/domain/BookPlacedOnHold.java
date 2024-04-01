package undecided.demo.borrow.domain;

import java.time.LocalDate;
import java.util.UUID;
import org.jmolecules.event.annotation.DomainEvent;

@DomainEvent
public record BookPlacedOnHold(UUID holdId,
                               String inventoryNumber,
                               LocalDate dateOfHold) {

}
