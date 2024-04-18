package undecided.demo.relationship.appl.command.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import undecided.demo.relationship.model.employee.Employee;
import undecided.demo.relationship.model.employee.EmployeeRepository;

@Component
@RequiredArgsConstructor
public class AddEmployeeCommand {

  private final EmployeeRepository employeeRepository;

  public Employee execute(Employee employee) {
    return employeeRepository.save(employee);
  }
}
