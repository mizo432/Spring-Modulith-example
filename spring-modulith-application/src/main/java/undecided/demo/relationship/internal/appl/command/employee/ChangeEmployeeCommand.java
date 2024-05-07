package undecided.demo.relationship.internal.appl.command.employee;

import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import undecided.demo.relationship.EmployeeChanged;
import undecided.demo.relationship.internal.model.employee.Employee;
import undecided.demo.relationship.internal.model.employee.EmployeeRepository;

@Service
@RequiredArgsConstructor
public class ChangeEmployeeCommand {

  private final EmployeeRepository employeeRepository;
  private final ApplicationEventPublisher applicationEventPublisher;

  public void execute(Employee employee) {
    Optional<Employee> oldEmployeeOptional = employeeRepository.findById(employee.getId());
    if (oldEmployeeOptional.isPresent()) {
      employeeRepository.save(employee);
      applicationEventPublisher.publishEvent(new EmployeeChanged(employee));
      
    }

    throw new EntityNotFoundException("employee not found.");

  }
}
