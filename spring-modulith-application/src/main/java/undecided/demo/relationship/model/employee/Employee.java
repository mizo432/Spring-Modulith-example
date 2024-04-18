package undecided.demo.relationship.model.employee;

import static org.hibernate.validator.internal.util.CollectionHelper.newArrayList;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import lombok.NoArgsConstructor;
import undecided.demo.relationship.model.party.PartyId;

public record Employee(PartyId id, EmployeeCode code) {

  public static final Employee EMPTY = new Employee(PartyId.EMPTY, EmployeeCode.EMPTY);

  public static Employee reconstruct(Long id, String code) {
    return new Employee(new PartyId(id), new EmployeeCode(code));
  }

  public boolean isEmpty() {
    return id.isEmpty();
  }

  ;

  @NoArgsConstructor
  public static class Employees {

    private final List<Employee> value = newArrayList();

    public static final Employees EMPTY = new Employees();

    Employees(Collection<Employee> value) {
      this.value.addAll(value);
    }

    public static Employees reconstruct(List<Employee> value) {
      return new Employees(value);

    }

    public List<Employee> getValue() {
      return Collections.unmodifiableList(value);
    }
  }
}
