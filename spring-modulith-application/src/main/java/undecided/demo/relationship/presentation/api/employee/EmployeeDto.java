package undecided.demo.relationship.presentation.api.employee;

import java.util.List;
import java.util.stream.Collectors;
import lombok.NoArgsConstructor;
import undecided.demo.relationship.model.employee.Employee;
import undecided.demo.relationship.model.employee.Employee.Employees;

@NoArgsConstructor
public class EmployeeDto {

  private Long employeeId;

  public static EmployeeDto convertFromEntity(Employee employee) {
    return new EmployeeDto();
  }

  public static List<EmployeeDto> convertFromEntities(Employees values) {
    return values.getValue().stream()
        .map(EmployeeDto::convertFromEntity)
        .collect(Collectors.toList());
  }

}
