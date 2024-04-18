package undecided.demo.borrow.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PatronTest {

  /**
   * Method under test: {@link Patron#of(Patron.Membership)}
   */
  @Test
  void of() {
    // Arrange and Act
    Patron actualOfResult = Patron.of(Patron.Membership.ACTIVE);

    // Assert
    assertEquals(2, actualOfResult.getId().id().variant());
    assertEquals(Patron.Membership.ACTIVE, actualOfResult.getStatus());
  }

  /**
   * Method under test: {@link Patron#of(Patron.Membership)}
   */
  @Test
  void of2() {
    // Arrange and Act
    Patron actualOfResult = Patron.of(Patron.Membership.INACTIVE);

    // Assert
    assertEquals(2, actualOfResult.getId().id().variant());
    assertEquals(Patron.Membership.INACTIVE, actualOfResult.getStatus());
  }

  /**
   * Method under test: {@link Patron#deactivate()}
   */
  @Test
  void deactivate() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: Missing observers.
    //   Diffblue Cover was unable to create an assertion.
    //   Add getters for the following fields or make them package-private:
    //     Patron.id
    //     Patron.status

    // Arrange and Act
    Patron.of(Patron.Membership.ACTIVE).deactivate();
  }
}
