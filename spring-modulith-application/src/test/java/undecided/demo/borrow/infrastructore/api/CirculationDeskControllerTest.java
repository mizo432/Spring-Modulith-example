package undecided.demo.borrow.infrastructore.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
import java.util.function.UnaryOperator;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import undecided.demo.borrow.application.CirculationDeskCommand;
import undecided.demo.borrow.application.CirculationDeskQuery;
import undecided.demo.borrow.domain.Book;
import undecided.demo.borrow.domain.BookRepository;
import undecided.demo.borrow.domain.Hold;
import undecided.demo.borrow.domain.HoldEventPublisher;
import undecided.demo.borrow.domain.HoldRepository;
import undecided.demo.borrow.domain.Patron;
import undecided.demo.borrow.infrastructore.dao.BookJpaRepository;
import undecided.demo.borrow.infrastructore.dao.HoldEntity;
import undecided.demo.borrow.infrastructore.dao.HoldJpaRepository;
import undecided.demo.borrow.infrastructore.datasource.BookRepositoryAdapter;
import undecided.demo.borrow.infrastructore.datasource.HoldRepositoryAdapter;

@ContextConfiguration(classes = {CirculationDeskController.class, CirculationDeskQuery.class,
    CirculationDeskCommand.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class CirculationDeskControllerTest {

  @MockBean
  private BookRepository bookRepository;

  @Autowired
  private CirculationDeskController circulationDeskController;

  @MockBean
  private HoldEventPublisher holdEventPublisher;

  @MockBean
  private HoldRepository holdRepository;

  /**
   * Method under test:
   * {@link CirculationDeskController#holdBook(CirculationDeskController.HoldRequest)}
   */
  @Test
  void holdBook() {
    //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

    // Arrange
    Hold hold = mock(Hold.class);
    when(hold.getDateOfHold()).thenReturn(LocalDate.of(1970, 1, 1));
    when(hold.getHeldBy()).thenReturn(new Patron.PatronId(UUID.randomUUID()));
    when(hold.getOnBook()).thenReturn(new Book.Barcode("Barcode"));
    when(hold.getId()).thenReturn(new Hold.HoldId(UUID.randomUUID()));
    CirculationDeskCommand circulationDeskCommand = mock(CirculationDeskCommand.class);
    when(circulationDeskCommand.createAndPublishHold(Mockito.<Hold.PlaceHold>any())).thenReturn(
        hold);
    CirculationDeskController circulationDeskController = new CirculationDeskController(
        new CirculationDeskQuery(new HoldRepositoryAdapter(mock(HoldJpaRepository.class))),
        circulationDeskCommand);

    // Act
    ResponseEntity<HoldDto> actualHoldBookResult = circulationDeskController
        .holdBook(new CirculationDeskController.HoldRequest("Barcode", UUID.randomUUID()));

    // Assert
    verify(circulationDeskCommand).createAndPublishHold(isA(Hold.PlaceHold.class));
    verify(hold).getDateOfHold();
    verify(hold).getHeldBy();
    verify(hold).getId();
    verify(hold).getOnBook();
    HoldDto body = actualHoldBookResult.getBody();
    assertEquals("1970-01-01", body.getDateOfHold().toString());
    assertEquals("Barcode", body.getBookBarcode());
    assertEquals(200, actualHoldBookResult.getStatusCodeValue());
    assertTrue(actualHoldBookResult.hasBody());
    assertTrue(actualHoldBookResult.getHeaders().isEmpty());
  }

  /**
   * Method under test: {@link CirculationDeskController#viewSingleHold(UUID)}
   */
  @Test
  void viewSingleHold() {
    //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

    // Arrange
    HoldJpaRepository holds = mock(HoldJpaRepository.class);
    Optional<HoldEntity> ofResult = Optional.of(new HoldEntity());
    when(holds.findById(Mockito.<UUID>any())).thenReturn(ofResult);
    CirculationDeskQuery circulationDeskQuery = new CirculationDeskQuery(
        new HoldRepositoryAdapter(holds));
    BookRepositoryAdapter bookRepository = new BookRepositoryAdapter(mock(BookJpaRepository.class));
    CirculationDeskController circulationDeskController = new CirculationDeskController(
        circulationDeskQuery,
        new CirculationDeskCommand(bookRepository,
            new HoldRepositoryAdapter(mock(HoldJpaRepository.class)),
            mock(HoldEventPublisher.class)));

    // Act
    ResponseEntity<HoldDto> actualViewSingleHoldResult = circulationDeskController.viewSingleHold(
        UUID.randomUUID());

    // Assert
    verify(holds).findById(isA(UUID.class));
    assertNull(actualViewSingleHoldResult.getBody());
    assertEquals(404, actualViewSingleHoldResult.getStatusCodeValue());
    assertTrue(actualViewSingleHoldResult.getHeaders().isEmpty());
  }

  /**
   * Method under test: {@link CirculationDeskController#viewSingleHold(UUID)}
   */
  @Test
  void viewSingleHold2() {
    //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

    // Arrange
    Hold hold = mock(Hold.class);
    when(hold.getDateOfHold()).thenReturn(LocalDate.of(1970, 1, 1));
    when(hold.getHeldBy()).thenReturn(new Patron.PatronId(UUID.randomUUID()));
    when(hold.getOnBook()).thenReturn(new Book.Barcode("Barcode"));
    when(hold.getId()).thenReturn(new Hold.HoldId(UUID.randomUUID()));
    Optional<Hold> ofResult = Optional.of(hold);
    CirculationDeskQuery circulationDeskQuery = mock(CirculationDeskQuery.class);
    when(circulationDeskQuery.locate(Mockito.<UUID>any())).thenReturn(ofResult);
    BookRepositoryAdapter bookRepository = new BookRepositoryAdapter(mock(BookJpaRepository.class));
    CirculationDeskController circulationDeskController = new CirculationDeskController(
        circulationDeskQuery,
        new CirculationDeskCommand(bookRepository,
            new HoldRepositoryAdapter(mock(HoldJpaRepository.class)),
            mock(HoldEventPublisher.class)));

    // Act
    ResponseEntity<HoldDto> actualViewSingleHoldResult = circulationDeskController.viewSingleHold(
        UUID.randomUUID());

    // Assert
    verify(circulationDeskQuery).locate(isA(UUID.class));
    verify(hold).getDateOfHold();
    verify(hold).getHeldBy();
    verify(hold).getId();
    verify(hold).getOnBook();
    HoldDto body = actualViewSingleHoldResult.getBody();
    assertEquals("1970-01-01", body.getDateOfHold().toString());
    assertEquals("Barcode", body.getBookBarcode());
    assertEquals(200, actualViewSingleHoldResult.getStatusCodeValue());
    assertTrue(actualViewSingleHoldResult.hasBody());
    assertTrue(actualViewSingleHoldResult.getHeaders().isEmpty());
  }

  /**
   * Method under test:
   * {@link CirculationDeskController#holdBook(CirculationDeskController.HoldRequest)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void holdBook2() throws Exception {

    // Arrange
    Hold hold = mock(Hold.class);
    Hold hold2 = mock(Hold.class);
    when(hold2.getDateOfHold()).thenReturn(LocalDate.of(1970, 1, 1));
    when(hold2.getHeldBy()).thenReturn(new Patron.PatronId(UUID.randomUUID()));
    when(hold2.getOnBook()).thenReturn(new Book.Barcode("Barcode"));
    when(hold2.getId()).thenReturn(new Hold.HoldId(UUID.randomUUID()));
    when(hold.then(Mockito.<UnaryOperator<Hold>>any())).thenReturn(hold2);
    when(holdRepository.save(Mockito.<Hold>any())).thenReturn(hold);
    Optional<Book> ofResult = Optional.of(mock(Book.class));
    when(bookRepository.findAvailableBook(Mockito.<Book.Barcode>any())).thenReturn(ofResult);
    MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/borrow/holds")
        .contentType(MediaType.APPLICATION_JSON);
    CirculationDeskController.HoldRequest holdRequest = new CirculationDeskController.HoldRequest(
        "Barcode",
        UUID.randomUUID());

    MockHttpServletRequestBuilder requestBuilder = contentTypeResult
        .content((new ObjectMapper()).writeValueAsString(holdRequest));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(circulationDeskController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"id\":\"a248ec28-b3a3-4ee4-bc34-da3b1f452070\",\"bookBarcode\":\"Barcode\",\"patronId\":\"e20b7db1-29ad-402c"
                    + "-adbc-cd2bb5fc84e4\",\"dateOfHold\":[1970,1,1]}"));
  }

  /**
   * Method under test: {@link CirculationDeskController#viewSingleHold(UUID)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void viewSingleHold3() throws Exception {

    // Arrange
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/borrow/holds/{id}",
        UUID.randomUUID());

    // Act
    MockMvcBuilders.standaloneSetup(circulationDeskController).build().perform(requestBuilder);
  }
}
