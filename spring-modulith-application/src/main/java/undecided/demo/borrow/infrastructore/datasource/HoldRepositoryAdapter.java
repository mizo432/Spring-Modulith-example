package undecided.demo.borrow.infrastructore.datasource;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import undecided.demo.borrow.domain.Hold;
import undecided.demo.borrow.domain.HoldRepository;
import undecided.demo.borrow.infrastructore.dao.HoldEntity;
import undecided.demo.borrow.infrastructore.dao.HoldJpaRepository;

@Component
@Transactional
@RequiredArgsConstructor
public class HoldRepositoryAdapter implements HoldRepository {

  private final HoldJpaRepository holds;

  @Override
  public Hold save(Hold hold) {
    holds.save(HoldEntity.fromDomain(hold));
    return hold;
  }

  @Override
  public Optional<Hold> findById(Hold.HoldId id) {
    return holds.findById(id.id())
        .map(HoldEntity::toDomain);
  }

  @Override
  public List<Hold> activeHolds() {
    return null;
  }
}
