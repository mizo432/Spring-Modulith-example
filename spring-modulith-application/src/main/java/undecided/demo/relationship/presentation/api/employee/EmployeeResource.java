package undecided.demo.relationship.presentation.api.employee;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import undecided.common.primitive.Strings2;
import undecided.demo.relationship.appl.command.employee.AddEmployeeCommand;
import undecided.demo.relationship.appl.query.employee.EmployeeQuery;
import undecided.demo.relationship.model.employee.Employee;
import undecided.demo.relationship.model.employee.EmployeeCode;
import undecided.demo.relationship.model.party.PartyId;

@RestController
@RequiredArgsConstructor
public class EmployeeResource {

  private final EmployeeQuery employeeQuery;
  private final AddEmployeeCommand addEmployeeCommand;
  private final ObjectMapper objectMapper;


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

  @PostMapping(path = "api/v1/employees")
  ResponseEntity<EmployeeDto> post(@RequestBody EmployeeDto employeeDto) {
    Employee employee = employeeDto.toEntity();
    EmployeeDto result = EmployeeDto.convertFromEntity(addEmployeeCommand.execute(employee));
    return ResponseEntity.created(URI.create("api/v1/employees/" + result.id())).body(result);

  }

  @GetMapping(path = "api/v1/employee/{employeeIdOrEmployeeCode}")
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
    EmployeeDto employeeDto = EmployeeDto.convertFromEntity(employee);
    try {
//      Resource resource = new PathResource("filename");
//      Resource resource = new InputStreamResource(inputStream);

      Resource resource = new ByteArrayResource(
          objectMapper.writeValueAsString(employeeDto).getBytes());
//      return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF)
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
