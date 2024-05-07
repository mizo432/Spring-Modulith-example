package undecided.demo.relationship.internal.presentation.api.employee;

import static undecided.common.primitive.Strings2.surroundSilently;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import undecided.common.primitive.Strings2;
import undecided.demo.relationship.internal.appl.command.employee.AddEmployeeCommand;
import undecided.demo.relationship.internal.appl.command.employee.ChangeEmployeeCommand;
import undecided.demo.relationship.internal.appl.query.employee.EmployeeQuery;
import undecided.demo.relationship.internal.model.employee.Employee;
import undecided.demo.relationship.internal.model.employee.EmployeeCode;
import undecided.demo.relationship.internal.model.party.PartyId;

@RestController
@RequiredArgsConstructor
public class EmployeeResource {

  private final EmployeeQuery employeeQuery;
  private final AddEmployeeCommand addEmployeeCommand;
  private final ObjectMapper objectMapper;
  private final ChangeEmployeeCommand changeEmployeeCommand;

  @GetMapping(path = "api/v1/employees")
  ResponseEntity<List<Employee>> getAll() {
    return ResponseEntity.ok(employeeQuery.getAll().getValue());

  }

  @GetMapping(path = "api/v1/employee/{employeeIdOrEmployeeCode}")
  ResponseEntity<Employee> findByEmployeeIdOrEmployeeCode(
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
    return ResponseEntity.ok(employee);

  }

  @PostMapping(path = "api/v1/employees")
  ResponseEntity<Employee> post(@RequestBody Employee employee) {
    Employee result = addEmployeeCommand.execute(employee.create());
    return ResponseEntity.created(URI.create("api/v1/employees/" + result.getId())).body(result);

  }

  @PutMapping(path = "api/v1/employees")
  ResponseEntity<Void> put(@RequestBody Employee employee) {
    changeEmployeeCommand.execute(employee);
    return ResponseEntity.noContent().build();

  }

  @GetMapping(path = "api/v1/employee/{employeeIdOrEmployeeCode}/json")
  ResponseEntity<Resource> downloadFile(
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
    try {

      Resource resource = new ByteArrayResource(
          objectMapper.writeValueAsString(employee).getBytes());
      return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
          .header(HttpHeaders.CONTENT_DISPOSITION,
              "attachment; filename=" + surroundSilently(resource.getFilename(),
                  Strings2.DOUBLE_QUOTE))
          .body(resource);
    } catch (JsonProcessingException e) {
      return ResponseEntity.internalServerError().build();
    }

  }

}
