package undecided.demo.borrow.infrastructore.in;

import java.time.LocalDate;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import undecided.demo.borrow.application.CirculationDesk;
import undecided.demo.borrow.application.HoldDto;
import undecided.demo.borrow.domain.Book;
import undecided.demo.borrow.domain.Hold;
import undecided.demo.borrow.domain.Patron.PatronId;

@RestController
@RequiredArgsConstructor
public class CirculationDeskController {

  private final CirculationDesk circulationDesk;

  @PostMapping("/borrow/holds")
  ResponseEntity<HoldDto> holdBook(@RequestBody HoldRequest request) {
    var command = new Hold.PlaceHold(new Book.Barcode(request.barcode()), LocalDate.now(),
        new PatronId(request.patronId()));
    var holdDto = circulationDesk.placeHold(command);
    return ResponseEntity.ok(holdDto);
  }

  @GetMapping("/borrow/holds/{id}")
  ResponseEntity<HoldDto> viewSingleHold(@PathVariable("id") UUID holdId) {
    return circulationDesk.locate(holdId)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  record HoldRequest(String barcode, UUID patronId) {

  }
}
