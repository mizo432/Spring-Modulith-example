package undecided.shared.model.relationship.party.party;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * This class represents a PartyId.
 *
 * <p>
 * A PartyId is an identifier used to identify a party or entity within a system. It is represented
 * by a Long value.
 * </p>
 *
 * <p>
 * This class provides an implementation for the toString() method, which returns a string
 * representation of the PartyId's value.
 * </p>
 *
 * <p>
 * This class has a private constructor with access level set to private, and a static factory
 * method named "reconstruct" that can be used to create instances of PartyId.
 * </p>
 *
 * <p>
 * This class also provides a getter and setter method for the value property, allowing the value of
 * the PartyId to be accessed and modified.
 * </p>
 *
 * <p>
 * Example usage:
 * </p>
 *
 * <pre>{@code
 * PartyId partyId = PartyId.reconstruct(12345L);
 * System.out.println(partyId.toString()); // Output: "12345"
 * }</pre>
 */
@AllArgsConstructor(access = AccessLevel.PACKAGE, staticName = "reconstruct")
@Data
public class PartyId {

  private Long value;

  /**
   * Returns a string representation of the PartyId's value.
   *
   * @return A string representation of the PartyId's value.
   */
  public String toString() {
    return String.valueOf(value);
  }

}
