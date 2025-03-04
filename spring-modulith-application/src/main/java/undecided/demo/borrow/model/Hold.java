package undecided.demo.borrow.model;

import java.time.LocalDate;
import java.util.UUID;
import java.util.function.UnaryOperator;
import lombok.Getter;
import org.jmolecules.ddd.types.Identifier;
import undecided.demo.borrow.model.Patron.PatronId;

@Getter
public class Hold {

  private final HoldId id;

  private final Book.Barcode onBook;

  private final PatronId heldBy;

  private final LocalDate dateOfHold;

  private Hold(PlaceHold placeHold) {
    this.id = new HoldId(UUID.randomUUID());
    this.onBook = placeHold.inventoryNumber();
    this.dateOfHold = placeHold.dateOfHold();
    this.heldBy = placeHold.patronId();
  }

  public static Hold placeHold(PlaceHold command) {
    return new Hold(command);
  }

  /**
   * Applies a unary operator to the current Hold instance and returns the result.
   *
   * @param function the unary operator to apply
   * @return the result of applying the unary operator to the current Hold instance
   */
  public Hold then(UnaryOperator<Hold> function) {
    return function.apply(this);
  }

  public record HoldId(UUID id) implements Identifier {

  }

  public record PlaceHold(Book.Barcode inventoryNumber, LocalDate dateOfHold, PatronId patronId) {

  }
}