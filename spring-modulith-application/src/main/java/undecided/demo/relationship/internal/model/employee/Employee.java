package undecided.demo.relationship.internal.model.employee;

import static org.hibernate.validator.internal.util.CollectionHelper.newArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import undecided.demo.relationship.internal.model.party.PartyId;

@Entity
@Getter
@Setter
@Table(name = "employees")
@NoArgsConstructor(force = true)
public final class Employee {

  @Transient
  public static final Employee EMPTY = new Employee(PartyId.EMPTY, EmployeeCode.EMPTY,
      EmployeeName.EMPTY);

  @Id
  @EmbeddedId
  @Column(name = "partyId", nullable = false)
  @AttributeOverrides({
      @AttributeOverride(name = "value", column = @Column(name = "partyId"))
  })
  private PartyId id = PartyId.EMPTY;

  @Embedded
  private EmployeeCode code = EmployeeCode.EMPTY;

  @Embedded
  private EmployeeName name = EmployeeName.EMPTY;

  public Employee(@NonNull PartyId id, @NonNull EmployeeCode code, @NonNull EmployeeName name) {
    this.id = id;
    this.code = code;
    this.name = name;
  }

  @JsonIgnore
  @Transient
  public boolean isEmpty() {
    return id.isEmpty();
  }

  public Employee create() {
    return new Employee(PartyId.newInstance(), code, name);

  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj == null || obj.getClass() != this.getClass()) {
      return false;
    }
    var that = (Employee) obj;
    return Objects.equals(this.id, that.id) &&
        Objects.equals(this.code, that.code) &&
        Objects.equals(this.name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, code, name);
  }

  @Override
  public String toString() {
    return "Employee[" +
        "id=" + id + ", " +
        "code=" + code + ", " +
        "name=" + name + ']';
  }


  ;

  @NoArgsConstructor
  public static class Employees {

    private final List<Employee> value = newArrayList();

    public static final Employees EMPTY = new Employees();

    Employees(Collection<Employee> value) {
      this.value.addAll(value);
    }

    public static @NonNull Employees reconstruct(@NonNull Iterable<Employee> value) {
      return new Employees(newArrayList(value));

    }

    public List<Employee> getValue() {
      return Collections.unmodifiableList(value);
    }
  }
}
