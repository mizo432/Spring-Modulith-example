package undecided.demo.relationship.internal.model.employee;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(force = true)
public final class EmployeeName {

  public static final EmployeeName EMPTY = new EmployeeName(null);

  @Column(name = "name", unique = true, nullable = false, length = 50)
  private String value;

  public EmployeeName(String value) {
    this.value = value;
  }

  public static EmployeeName of(String value) {
    return new EmployeeName(value);

  }

  @JsonValue
  public String getValue() {
    return value;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj == null || obj.getClass() != this.getClass()) {
      return false;
    }
    var that = (EmployeeName) obj;
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
