package undecided.demo.relationship.model.party;

import undecided.common.primitive.Objects2;

public record PartyId(Long value) {

  public static final PartyId EMPTY = new PartyId(null);

  public static PartyId of(Long value) {
    return new PartyId(value);

  }

  public boolean isEmpty() {
    return Objects2.isNull(value);
  }
}
