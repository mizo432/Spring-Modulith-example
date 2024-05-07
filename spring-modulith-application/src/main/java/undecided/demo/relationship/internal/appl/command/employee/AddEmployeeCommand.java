package undecided.demo.relationship.internal.appl.command.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import undecided.demo.relationship.EmployeeAddedToCompany;
import undecided.demo.relationship.internal.model.employee.Employee;
import undecided.demo.relationship.internal.model.employee.EmployeeRepository;

@Component
@RequiredArgsConstructor
public class AddEmployeeCommand {

  private final EmployeeRepository employeeRepository;
  private final ApplicationEventPublisher applicationEventPublisher;

  public Employee execute(Employee employee) {
    Employee result = employeeRepository.save(employee);
    EmployeeAddedToCompany addedToCompany = new EmployeeAddedToCompany(employee);
    applicationEventPublisher.publishEvent(addedToCompany);
    return result;
  }
}
