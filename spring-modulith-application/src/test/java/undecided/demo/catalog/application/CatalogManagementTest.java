package undecided.demo.catalog.application;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import undecided.demo.catalog.domain.CatalogBook;
import undecided.demo.catalog.domain.CatalogRepository;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class CatalogManagementTest {

  @MockBean
  private ApplicationEventPublisher applicationEventPublisher;

  @MockBean
  private BookMapper bookMapper;

  @Autowired
  private CatalogManagement catalogManagement;

  @MockBean
  private CatalogRepository catalogRepository;

  /**
   * Method under test:
   * {@link CatalogManagement#addToCatalog(String, CatalogBook.Barcode, String, String)}
   */
  @Test
  void addToCatalog() {
    // Arrange
    when(catalogRepository.save(Mockito.<CatalogBook>any())).thenReturn(new CatalogBook());
    CatalogBook.Barcode catalogNumber = new CatalogBook.Barcode("Barcode");
    BookDto bookDto = new BookDto(1L, "Dr", catalogNumber, "Isbn", new CatalogBook.Author("Name"));

    when(bookMapper.toDto(Mockito.<CatalogBook>any())).thenReturn(bookDto);

    // Act
    BookDto actualAddToCatalogResult = catalogManagement.addToCatalog("Dr",
        new CatalogBook.Barcode("Barcode"), "Isbn",
        "JaneDoe");

    // Assert
    verify(catalogRepository).save(isA(CatalogBook.class));
    verify(bookMapper).toDto(isA(CatalogBook.class));
    assertSame(bookDto, actualAddToCatalogResult);
  }

  /**
   * Method under test: {@link CatalogManagement#locate(Long)}
   */
  @Test
  void locate() {
    // Arrange
    Optional<CatalogBook> ofResult = Optional.of(new CatalogBook());
    when(catalogRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

    // Act
    Optional<BookDto> actualLocateResult = catalogManagement.locate(1L);

    // Assert
    verify(catalogRepository).findById(isA(Long.class));
    BookDto getResult = actualLocateResult.get();
    assertNull(getResult.id());
    assertNull(getResult.isbn());
    assertNull(getResult.title());
    assertNull(getResult.author());
    assertNull(getResult.catalogNumber());
  }

  /**
   * Method under test: {@link CatalogManagement#fetchBooks()}
   */
  @Test
  void fetchBooks() {
    // Arrange, Act and Assert
    assertTrue(catalogManagement.fetchBooks().isEmpty());
  }
}
