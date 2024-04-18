package undecided.demo.relationship.presentation.api.employee;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import undecided.common.primitive.Strings2;
import undecided.demo.relationship.appl.query.employee.EmployeeQuery;
import undecided.demo.relationship.model.employee.Employee;
import undecided.demo.relationship.model.employee.EmployeeCode;
import undecided.demo.relationship.model.party.PartyId;

@RestController
@RequiredArgsConstructor
public class EmployeeResource {

  private final EmployeeQuery employeeQuery;


  @GetMapping(path = "api/v1/employees")
  ResponseEntity<List<EmployeeDto>> getAll() {
    return ResponseEntity.ok(EmployeeDto.convertFromEntities(employeeQuery.getAll()));

  }

  @GetMapping(path = "api/v1/employee/{employeeIdOrEmployeeCode}")
  ResponseEntity<EmployeeDto> findByEmployeeIdOrEmployeeCode(
      @PathVariable("employeeIdOrEmployeeCode") String employeeIdOrEmployeeCode) {
    Employee employee;
    if (Strings2.isDigit(employeeIdOrEmployeeCode)) {
      employee = employeeQuery.findById(
          PartyId.of(Long.valueOf(employeeIdOrEmployeeCode)));

    } else {
      employee = employeeQuery.findByCode(
          EmployeeCode.of(employeeIdOrEmployeeCode));
    }

    if (employee.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    EmployeeDto employeeDto = EmployeeDto.convertFromEntity(employee);
    return ResponseEntity.ok(employeeDto);

  }

}
