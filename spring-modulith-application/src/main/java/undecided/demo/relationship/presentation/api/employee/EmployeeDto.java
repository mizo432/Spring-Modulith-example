package undecided.demo.relationship.presentation.api.employee;

import java.util.List;
import java.util.stream.Collectors;
import undecided.demo.relationship.model.employee.Employee;
import undecided.demo.relationship.model.employee.Employee.Employees;

public record EmployeeDto(Long id, String code, String name) {

  public static EmployeeDto convertFromEntity(Employee employee) {
    return new EmployeeDto(
        employee.id().value(),
        employee.code().value(),
        null);
  }

  public static List<EmployeeDto> convertFromEntities(Employees values) {
    return values.getValue().stream()
        .map(EmployeeDto::convertFromEntity)
        .collect(Collectors.toList());
  }

  public Employee toEntity() {
    return Employee.create(id, code, name);
  }
}
