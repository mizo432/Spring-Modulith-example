package undecided.common.checker;

import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ObjectChecker {

  /**
   * Checks if the given reference is not null. If the reference is null, throws the exception
   * provided by the exceptionSupplier.
   *
   * @param reference The reference to check for nullity.
   * @param exceptionSupplier A supplier that provides the exception to be thrown if the reference
   * is null.
   * @return The non-null reference.
   * @throws RuntimeException if the reference is null.
   */
  public static <T> @NotNull T checkNotNull(@Nullable T reference,
      @NotNull Supplier<RuntimeException> exceptionSupplier) {
    if (reference == null) {
      throw exceptionSupplier.get();
    }
    return reference;

  }

}
