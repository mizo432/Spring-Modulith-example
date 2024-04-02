package undecided.demo.borrow.infrastructore.dao;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jmolecules.ddd.annotation.Identity;
import undecided.demo.borrow.domain.Book;
import undecided.demo.borrow.domain.Hold;
import undecided.demo.borrow.domain.Patron.PatronId;

@SuppressWarnings("JpaDataSourceORMInspection")
@Getter
@Entity
@NoArgsConstructor
@Table(name = "borrow_holds")
public class HoldEntity {

  @Identity
  @Id
  private UUID id;

  @Embedded
  @AttributeOverride(name = "barcode", column = @Column(name = "bookBarcode"))
  private Book.Barcode book;

  private UUID patronId;

  private LocalDate dateOfHold;

  @Enumerated(EnumType.STRING)
  private HoldStatus status;

  @Version
  private Long version;

  public Hold toDomain() {
    if (this.status == HoldStatus.HOLDING) {
      return Hold.placeHold(new Hold.PlaceHold(book, dateOfHold, new PatronId(patronId)));
    } else {
      return null;
    }
  }

  public static HoldEntity fromDomain(Hold hold) {
    var entity = new HoldEntity();
    entity.id = hold.getId().id();
    entity.book = hold.getOnBook();
    entity.patronId = hold.getHeldBy().id();
    entity.dateOfHold = hold.getDateOfHold();
    entity.status = HoldStatus.HOLDING;
    entity.version = 0L;
    return entity;
  }
}

enum HoldStatus {
  HOLDING, ACTIVE, COMPLETED
}
