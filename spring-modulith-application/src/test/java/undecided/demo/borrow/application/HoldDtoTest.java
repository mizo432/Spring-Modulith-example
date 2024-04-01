package undecided.demo.borrow.application;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import undecided.demo.borrow.domain.Hold;

class HoldDtoTest {

  /**
   * Method under test: {@link HoldDto#from(Hold)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void from() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke "undecided.demo.borrow.domain.Hold.getId()" because "hold" is null
    //       at undecided.demo.borrow.application.HoldDto.from(HoldDto.java:24)
    //   See https://diff.blue/R013 to resolve this issue.

    // Arrange and Act
    HoldDto.from(null);
  }
}
