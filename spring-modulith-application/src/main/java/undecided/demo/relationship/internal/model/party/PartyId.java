package undecided.demo.relationship.internal.model.party;

import static java.util.Objects.nonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;
import java.util.Objects;
import lombok.NoArgsConstructor;
import undecided.common.primitive.Objects2;

@Embeddable
@NoArgsConstructor(force = true)
public final class PartyId {

  @Transient
  public static final PartyId EMPTY = new PartyId(null);
  
  @JsonValue
  private Long value;

  public PartyId(Long value) {
    this.value = value;
  }


  public static PartyId of(Long value) {
    return new PartyId(value);

  }

  public static PartyId newInstance() {
    return new PartyId(1L);
  }

  @JsonIgnore
  @Transient
  public boolean isEmpty() {
    return Objects2.isNull(value);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj == null || obj.getClass() != this.getClass()) {
      return false;
    }
    var that = (PartyId) obj;
    return Objects.equals(this.value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public String toString() {
    if (nonNull(value)) {
      return value.toString();
    }

    return null;
  }

}
