package undecided.demo.borrow.model;

public interface HoldEventPublisher {

  void holdPlaced(BookPlacedOnHold event);

  default Hold holdPlaced(Hold hold) {
    BookPlacedOnHold event = new BookPlacedOnHold(hold.getId().id(), hold.getOnBook().barcode(),
        hold.getDateOfHold());
    this.holdPlaced(event);
    return hold;
  }
}
