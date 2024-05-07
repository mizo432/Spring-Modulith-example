package undecided.demo.relationship.internal.infra.query.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import undecided.demo.relationship.internal.appl.query.employee.EmployeeQuery;
import undecided.demo.relationship.internal.model.employee.Employee;
import undecided.demo.relationship.internal.model.employee.Employee.Employees;
import undecided.demo.relationship.internal.model.employee.EmployeeCode;
import undecided.demo.relationship.internal.model.employee.EmployeeRepository;
import undecided.demo.relationship.internal.model.party.PartyId;

@Service
@RequiredArgsConstructor
public class EmployeeQueryImpl implements EmployeeQuery {

  private final EmployeeRepository employeeRepository;

  @Override
  public @NonNull Employees getAll() {
    Iterable<Employee> resultEmployees = employeeRepository.findAll();
    return Employees.reconstruct(resultEmployees);

  }

  /**
   * Retrieves an employee by their ID.
   *
   * @param id The ID of the employee to find.
   * @return The employee with the given ID, or employee.EMPTY if not found.
   * @throws NullPointerException if the id parameter is null.
   */
  @Override
  public @NonNull Employee findById(@NonNull PartyId id) {
    return employeeRepository.findById(id)
        .orElse(Employee.EMPTY);

  }

  /**
   * Finds an employee by their employee code.
   *
   * @param code The employee code of the employee to find.
   * @return The employee with the given employee code, or Employee.EMPTY if not found.
   * @throws NullPointerException if the code parameter is null.
   */
  @Override
  public @NonNull Employee findByCode(@NonNull EmployeeCode code) {
    return employeeRepository.findByCode(code)
        .orElse(Employee.EMPTY);
  }

}
