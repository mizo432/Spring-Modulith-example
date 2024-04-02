package undecided.demo.borrow.infrastructore.dao;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HoldJpaRepository extends JpaRepository<HoldEntity, UUID> {

}
