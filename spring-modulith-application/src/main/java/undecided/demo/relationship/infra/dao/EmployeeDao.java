package undecided.demo.relationship.infra.dao;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeDao extends CrudRepository<EmployeeRecord, Long> {

  Optional<EmployeeRecord> findByCode(String code);

}
