package undecided.demo.relationship.infra.datasource.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import undecided.demo.relationship.infra.dao.EmployeeDao;
import undecided.demo.relationship.infra.dao.EmployeeRecord;
import undecided.demo.relationship.model.employee.Employee;
import undecided.demo.relationship.model.employee.EmployeeRepository;

@Component
@RequiredArgsConstructor
public class EmployeeDataSource implements EmployeeRepository {

  private final EmployeeDao employeeDao;

  @Override
  public Employee save(Employee employee) {
    return employeeDao
        .save(EmployeeRecord.create(employee))
        .toEntity();
  }
}
