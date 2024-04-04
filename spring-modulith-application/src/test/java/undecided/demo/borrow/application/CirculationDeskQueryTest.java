package undecided.demo.borrow.application;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import undecided.demo.borrow.domain.Hold;
import undecided.demo.borrow.domain.HoldRepository;

@ContextConfiguration(classes = {CirculationDeskQuery.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class CirculationDeskQueryTest {

  @Autowired
  private CirculationDeskQuery circulationDeskQuery;

  @MockBean
  private HoldRepository holdRepository;

  /**
   * Method under test: {@link CirculationDeskQuery#locate(UUID)}
   */
  @Test
  void locate() {
    // Arrange
    Optional<Hold> ofResult = Optional.of(mock(Hold.class));
    when(holdRepository.findById(Mockito.<Hold.HoldId>any())).thenReturn(ofResult);

    // Act
    Optional<Hold> actualLocateResult = circulationDeskQuery.locate(UUID.randomUUID());

    // Assert
    verify(holdRepository).findById(isA(Hold.HoldId.class));
    assertSame(ofResult, actualLocateResult);
  }
}
