package undecided.demo.relationship.internal.model.employee;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;
import java.util.Objects;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(force = true)
public final class EmployeeCode {

  @Transient
  public static final EmployeeCode EMPTY = new EmployeeCode(null);

  @JsonValue
  @Column(name = "code", unique = true, nullable = false, length = 10)
  private final String value;

  public EmployeeCode(String value) {
    this.value = value;
  }

  public static EmployeeCode of(String value) {
    return new EmployeeCode(value);

  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj == null || obj.getClass() != this.getClass()) {
      return false;
    }
    var that = (EmployeeCode) obj;
    return Objects.equals(this.value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public String toString() {
    return value;
  }

}
