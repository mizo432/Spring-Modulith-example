package undecided.demo.relationship.infra.query.employee;

import static org.hibernate.validator.internal.util.CollectionHelper.newArrayList;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import undecided.demo.relationship.appl.query.employee.EmployeeQuery;
import undecided.demo.relationship.infra.dao.EmployeeDao;
import undecided.demo.relationship.infra.dao.EmployeeRecord;
import undecided.demo.relationship.model.employee.Employee;
import undecided.demo.relationship.model.employee.Employee.Employees;
import undecided.demo.relationship.model.employee.EmployeeCode;
import undecided.demo.relationship.model.party.PartyId;

@Service
@RequiredArgsConstructor
public class EmployeeQueryImpl implements EmployeeQuery {

  private final EmployeeDao employeeDao;

  @Override
  public @NonNull Employees getAll() {
    Iterable<EmployeeRecord> employeesRecord = employeeDao.findAll();
    List<Employee> employees = newArrayList();
    employeesRecord.forEach(
        employeeTable -> employees.add(employeeTable.toEntity()));
    return Employees.reconstruct(employees);

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
    return employeeDao.findById(id.value())
        .map(EmployeeRecord::toEntity)
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
    return employeeDao.findByCode(
            code.value())
        .map(EmployeeRecord::toEntity)
        .orElse(Employee.EMPTY);
  }

}
