package undecided.demo.relationship.internal.model.employee;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import undecided.demo.relationship.internal.model.party.PartyId;

public interface EmployeeRepository extends JpaRepository<Employee, PartyId> {


  Optional<Employee> findByCode(EmployeeCode code);

}
