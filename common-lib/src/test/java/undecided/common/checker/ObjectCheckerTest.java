package undecided.common.checker;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

class ObjectCheckerTest {

  @Test
  void testCheckNotNullWithNonNullReference() {
    // Test if the checkNotNull method does not throw an exception when a non-null reference is provided.
    assertDoesNotThrow(() -> ObjectChecker.checkNotNull("non-null",
        () -> new RuntimeException("Should not be thrown.")));
  }

  @Test
  void testCheckNotNullWithNullReference() {
    // Test if the checkNotNull method properly throws the exception provided by the Supplier when a null reference is provided.
    assertThatExceptionOfType(RuntimeException.class).isThrownBy(
        () -> ObjectChecker.checkNotNull(null,
            () -> new RuntimeException("This exception should be thrown.")));
  }
}
