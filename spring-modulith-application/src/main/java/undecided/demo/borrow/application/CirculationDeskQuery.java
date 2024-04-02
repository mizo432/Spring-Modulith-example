package undecided.demo.borrow.application;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import undecided.demo.borrow.domain.Hold;
import undecided.demo.borrow.domain.Hold.HoldId;
import undecided.demo.borrow.domain.HoldRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class CirculationDeskQuery {

  private final HoldRepository holdRepository;

  public Optional<Hold> locate(UUID holdId) {
    return holdRepository.findById(new HoldId(holdId));

  }

}