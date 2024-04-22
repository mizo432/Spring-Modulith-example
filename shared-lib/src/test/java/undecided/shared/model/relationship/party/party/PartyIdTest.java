package undecided.shared.model.relationship.party.party;

import static org.assertj.core.api.Assertions.assertThat;

class PartyIdTest {

  @org.junit.jupiter.api.Test
  void testToStringWhenValueIsNull() {
    PartyId partyId = PartyId.reconstruct(null);
    assertThat(partyId.toString()).as("Expected toString to return \"null\" when value is null")
        .isEqualTo("null");
  }

  @org.junit.jupiter.api.Test
  void testToStringWhenValueIsPositive() {
    PartyId partyId = PartyId.reconstruct(12345L);
    assertThat(partyId.toString()).as(
        "Expected toString to return \"12345\" when value is positive").isEqualTo("12345");
  }

  @org.junit.jupiter.api.Test
  void testToStringWhenValueIsZero() {
    PartyId partyId = PartyId.reconstruct(0L);
    assertThat(partyId.toString()).as("Expected toString to return \"0\" when value is zero")
        .isEqualTo("0");
  }

  @org.junit.jupiter.api.Test
  void testToStringWhenValueIsNegative() {
    PartyId partyId = PartyId.reconstruct(-12345L);
    assertThat(partyId.toString()).as(
        "Expected toString to return \"-12345\" when value is negative").isEqualTo("-12345");
  }
}
