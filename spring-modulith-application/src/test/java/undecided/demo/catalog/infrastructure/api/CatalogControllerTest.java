package undecided.demo.catalog.infrastructure.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import undecided.demo.catalog.application.CatalogManagementCommand;
import undecided.demo.catalog.application.CatalogManagementQuery;
import undecided.demo.catalog.model.CatalogBook;
import undecided.demo.catalog.model.CatalogRepository;

@ContextConfiguration(classes = {CatalogController.class, CatalogManagementCommand.class,
    CatalogManagementQuery.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class CatalogControllerTest {

  @Autowired
  private CatalogController catalogController;

  @MockBean
  private CatalogRepository catalogRepository;

  /**
   * Method under test: {@link CatalogController#viewSingleBook(Long)}
   */
  @Test
  void viewSingleBook() {
    // Arrange
    CatalogRepository catalogRepository = mock(CatalogRepository.class);
    Optional<CatalogBook> ofResult = Optional.of(new CatalogBook());
    when(catalogRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
    CatalogManagementQuery catalogManagementQuery = new CatalogManagementQuery(catalogRepository);

    // Act
    ResponseEntity<BookDto> actualViewSingleBookResult = (new CatalogController(
        new CatalogManagementCommand(mock(CatalogRepository.class),
            mock(ApplicationEventPublisher.class)),
        catalogManagementQuery)).viewSingleBook(1L);

    // Assert
    verify(catalogRepository).findById(isA(Long.class));
    BookDto body = actualViewSingleBookResult.getBody();
    assertThat(body.id()).isNull();
    assertThat(body.isbn()).isNull();
    assertThat(body.title()).isNull();
    assertThat(body.author()).isNull();
    assertThat(body.catalogNumber()).isNull();
    assertThat(actualViewSingleBookResult.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(actualViewSingleBookResult.hasBody()).isTrue();
    assertThat(actualViewSingleBookResult.getHeaders().isEmpty()).isTrue();
  }

  /**
   * Method under test: {@link CatalogController#viewSingleBook(Long)}
   */
  @Test
  void viewSingleBook2() {

    // Arrange
    CatalogRepository catalogRepository = mock(CatalogRepository.class);
    Optional<CatalogBook> emptyResult = Optional.empty();
    when(catalogRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
    CatalogManagementQuery catalogManagementQuery = new CatalogManagementQuery(catalogRepository);

    // Act
    ResponseEntity<BookDto> actualViewSingleBookResult = (new CatalogController(
        new CatalogManagementCommand(mock(CatalogRepository.class),
            mock(ApplicationEventPublisher.class)),
        catalogManagementQuery)).viewSingleBook(1L);

    // Assert
    verify(catalogRepository).findById(isA(Long.class));
    assertThat(actualViewSingleBookResult.getBody()).isNull();
    assertThat(actualViewSingleBookResult.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    assertThat(actualViewSingleBookResult.getHeaders().isEmpty()).isTrue();
  }

  /**
   * Method under test: {@link CatalogController#viewSingleBook(Long)}
   */
  @Test
  void viewSingleBook3() {

    // Arrange
    CatalogManagementQuery catalogManagementQuery = mock(CatalogManagementQuery.class);
    Optional<CatalogBook> ofResult = Optional.of(new CatalogBook());
    when(catalogManagementQuery.findById(Mockito.<Long>any())).thenReturn(ofResult);

    // Act
    ResponseEntity<BookDto> actualViewSingleBookResult = (new CatalogController(
        new CatalogManagementCommand(mock(CatalogRepository.class),
            mock(ApplicationEventPublisher.class)),
        catalogManagementQuery)).viewSingleBook(1L);

    // Assert
    verify(catalogManagementQuery).findById(isA(Long.class));
    BookDto body = actualViewSingleBookResult.getBody();
    assertThat(body.id()).isNull();
    assertThat(body.isbn()).isNull();
    assertThat(body.title()).isNull();
    assertThat(body.author()).isNull();
    assertThat(body.catalogNumber()).isNull();
    assertThat(actualViewSingleBookResult.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(actualViewSingleBookResult.hasBody()).isTrue();
    assertThat(actualViewSingleBookResult.getHeaders().isEmpty()).isTrue();
  }

  /**
   * Method under test:
   * {@link CatalogController#addBookToInventory(CatalogController.AddBookRequest)}
   */
  @Test
  void addBookToInventory() throws Exception {
    // Arrange
    CatalogBook.Barcode catalogNumber = new CatalogBook.Barcode("Barcode");
    when(catalogRepository.save(Mockito.<CatalogBook>any()))
        .thenReturn(new CatalogBook("Dr", catalogNumber, "Isbn", new CatalogBook.Author("Name")));
    MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/catalog/books")
        .contentType(MediaType.APPLICATION_JSON);

    ObjectMapper objectMapper = new ObjectMapper();
    MockHttpServletRequestBuilder requestBuilder = contentTypeResult
        .content(objectMapper.writeValueAsString(
            new CatalogController.AddBookRequest("Dr", "42", "Isbn", "JaneDoe")));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(catalogController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"id\":null,\"title\":\"Dr\",\"catalogNumber\":{\"barcode\":\"Barcode\"},\"isbn\":\"Isbn\",\"author\":{\"name"
                    + "\":\"Name\"}}"));
  }

  /**
   * Method under test: {@link CatalogController#viewBooks()}
   */
  @Test
  void viewBooks() {

    // Arrange
    CatalogRepository catalogRepository = mock(CatalogRepository.class);
    when(catalogRepository.findAll()).thenReturn(new ArrayList<>());
    CatalogManagementQuery catalogManagementQuery = new CatalogManagementQuery(catalogRepository);

    // Act
    ResponseEntity<List<BookDto>> actualViewBooksResult = (new CatalogController(
        new CatalogManagementCommand(mock(CatalogRepository.class),
            mock(ApplicationEventPublisher.class)),
        catalogManagementQuery)).viewBooks();

    // Assert
    verify(catalogRepository).findAll();
    assertThat(actualViewBooksResult.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(actualViewBooksResult.hasBody()).isTrue();
    assertThat(actualViewBooksResult.getHeaders().isEmpty()).isTrue();
  }

  /**
   * Method under test: {@link CatalogController#viewBooks()}
   */
  @Test
  void viewBooks2() {

    // Arrange
    ArrayList<CatalogBook> catalogBookList = new ArrayList<>();
    catalogBookList.add(new CatalogBook());
    CatalogRepository catalogRepository = mock(CatalogRepository.class);
    when(catalogRepository.findAll()).thenReturn(catalogBookList);
    CatalogManagementQuery catalogManagementQuery = new CatalogManagementQuery(catalogRepository);

    // Act
    ResponseEntity<List<BookDto>> actualViewBooksResult = (new CatalogController(
        new CatalogManagementCommand(mock(CatalogRepository.class),
            mock(ApplicationEventPublisher.class)),
        catalogManagementQuery)).viewBooks();

    // Assert
    verify(catalogRepository).findAll();
    BookDto getResult = actualViewBooksResult.getBody().get(0);
    assertThat(getResult.id()).isNull();
    assertThat(getResult.isbn()).isNull();
    assertThat(getResult.title()).isNull();
    assertThat(getResult.author()).isNull();
    assertThat(getResult.catalogNumber()).isNull();
    assertThat(actualViewBooksResult.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(actualViewBooksResult.hasBody()).isTrue();
    assertThat(actualViewBooksResult.getHeaders().isEmpty()).isTrue();
  }

  /**
   * Method under test: {@link CatalogController#viewBooks()}
   */
  @Test
  void viewBooks3() {
    //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

    // Arrange
    ArrayList<CatalogBook> catalogBookList = new ArrayList<>();
    catalogBookList.add(new CatalogBook());
    catalogBookList.add(new CatalogBook());
    CatalogRepository catalogRepository = mock(CatalogRepository.class);
    when(catalogRepository.findAll()).thenReturn(catalogBookList);
    CatalogManagementQuery catalogManagementQuery = new CatalogManagementQuery(catalogRepository);

    // Act
    ResponseEntity<List<BookDto>> actualViewBooksResult = (new CatalogController(
        new CatalogManagementCommand(mock(CatalogRepository.class),
            mock(ApplicationEventPublisher.class)),
        catalogManagementQuery)).viewBooks();

    // Assert
    verify(catalogRepository).findAll();
    List<BookDto> body = actualViewBooksResult.getBody();
    BookDto getResult = body.get(0);
    assertThat(getResult.id()).isNull();
    BookDto getResult2 = body.get(1);
    assertThat(getResult2.id()).isNull();
    assertThat(getResult.isbn()).isNull();
    assertThat(getResult2.isbn()).isNull();
    assertThat(getResult.title()).isNull();
    assertThat(getResult2.title()).isNull();
    assertThat(getResult.author()).isNull();
    assertThat(getResult2.author()).isNull();
    assertThat(getResult.catalogNumber()).isNull();
    assertThat(getResult2.catalogNumber()).isNull();
    assertThat(actualViewBooksResult.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(actualViewBooksResult.hasBody()).isTrue();
    assertThat(actualViewBooksResult.getHeaders().isEmpty()).isTrue();
  }

  /**
   * Method under test: {@link CatalogController#viewBooks()}
   */
  @Test
  void viewBooks4() {

    // Arrange
    CatalogManagementQuery catalogManagementQuery = mock(CatalogManagementQuery.class);
    when(catalogManagementQuery.findAll()).thenReturn(new ArrayList<>());

    // Act
    ResponseEntity<List<BookDto>> actualViewBooksResult = (new CatalogController(
        new CatalogManagementCommand(mock(CatalogRepository.class),
            mock(ApplicationEventPublisher.class)),
        catalogManagementQuery)).viewBooks();

    // Assert
    verify(catalogManagementQuery).findAll();
    assertThat(actualViewBooksResult.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(actualViewBooksResult.hasBody()).isTrue();
    assertThat(actualViewBooksResult.getHeaders().isEmpty()).isTrue();
  }

  /**
   * Method under test: {@link CatalogController#viewBooks()}
   */
  @Test
  void viewBooks5() {

    // Arrange
    ArrayList<CatalogBook> catalogBookList = new ArrayList<>();
    catalogBookList.add(null);
    CatalogManagementQuery catalogManagementQuery = mock(CatalogManagementQuery.class);
    when(catalogManagementQuery.findAll()).thenReturn(catalogBookList);

    // Act
    ResponseEntity<List<BookDto>> actualViewBooksResult = (new CatalogController(
        new CatalogManagementCommand(mock(CatalogRepository.class),
            mock(ApplicationEventPublisher.class)),
        catalogManagementQuery)).viewBooks();

    // Assert
    verify(catalogManagementQuery).findAll();
    assertThat(actualViewBooksResult.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(actualViewBooksResult.hasBody()).isTrue();
    assertThat(actualViewBooksResult.getHeaders().isEmpty()).isTrue();
  }

}
