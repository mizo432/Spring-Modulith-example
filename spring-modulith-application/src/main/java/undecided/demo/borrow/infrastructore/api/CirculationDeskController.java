package undecided.demo.borrow.infrastructore.api;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import undecided.demo.borrow.application.CirculationDeskCommand;
import undecided.demo.borrow.application.CirculationDeskQuery;
import undecided.demo.borrow.domain.Book;
import undecided.demo.borrow.domain.Hold;
import undecided.demo.borrow.domain.Patron.PatronId;

@RestController
@RequiredArgsConstructor
public class CirculationDeskController {

  private final CirculationDeskQuery circulationDeskQuery;
  private final CirculationDeskCommand circulationDeskCommand;

  @PostMapping("/borrow/holds")
  ResponseEntity<HoldDto> holdBook(@RequestBody HoldRequest request) {
    var command = new Hold.PlaceHold(new Book.Barcode(request.barcode()), LocalDate.now(),
        new PatronId(request.patronId()));
    var holdDto = HoldDto.from(circulationDeskCommand.createAndPublishHold(command));
    return ResponseEntity.ok(holdDto);
  }

  @GetMapping("/borrow/holds/{id}")
  ResponseEntity<HoldDto> viewSingleHold(@PathVariable("id") UUID holdId) {
    Optional<Hold> holdOptional = circulationDeskQuery.locate(holdId);
    return holdOptional.map(HoldDto::from)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  record HoldRequest(String barcode, UUID patronId) {

  }
}
