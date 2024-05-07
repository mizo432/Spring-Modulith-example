package undecided.demo.relationship.internal.appl.query.employee;

import org.springframework.lang.NonNull;
import undecided.demo.relationship.internal.model.employee.Employee;
import undecided.demo.relationship.internal.model.employee.Employee.Employees;
import undecided.demo.relationship.internal.model.employee.EmployeeCode;
import undecided.demo.relationship.internal.model.party.PartyId;

/**
 * Represents a query interface for retrieving employee information.
 * <p>
 * This interface provides methods for retrieving employee data based on different criteria.
 * <p>
 * All methods in this interface are expected to be implemented by concrete classes that implement
 * this interface.
 */
public interface EmployeeQuery {

  /**
   * Retrieves all employees.
   *
   * @return The Employees object containing a list of all employees.
   */
  @NonNull
  Employees getAll();

  /**
   * Finds an employee by their employee ID.
   *
   * @param employeeId The ID of the employee to find.
   * @return The employee with the given ID.
   * @throws NullPointerException if the id parameter is null.
   */
  @NonNull
  Employee findById(@NonNull PartyId employeeId);

  /**
   * Finds an employee by their employee code.
   *
   * @param employeeCode The employee code of the employee to find.
   * @return The employee with the given employee code.
   */
  @NonNull
  Employee findByCode(@NonNull EmployeeCode employeeCode);
}
