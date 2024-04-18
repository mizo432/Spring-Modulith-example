package undecided.demo.borrow.model;

import java.util.List;
import java.util.Optional;

public interface HoldRepository {

  Hold save(Hold hold);

//    Checkout save(Checkout checkout);

  Optional<Hold> findById(Hold.HoldId id);

  List<Hold> activeHolds();

}