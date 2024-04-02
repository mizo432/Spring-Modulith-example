package undecided.demo.catalog.application;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import undecided.demo.catalog.infrastructure.api.BookMapper;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class CatalogManagementCommandTest {

  @MockBean
  private ApplicationEventPublisher applicationEventPublisher;

  @MockBean
  private BookMapper bookMapper;

  @Autowired
  private CatalogManagementCommand catalogManagementCommand;

  @MockBean
  private CatalogRepository catalogRepository;

  /**
   * Method under test:
   * {@link CatalogManagementCommand#addToCatalog(String, CatalogBook.Barcode, String, String)}
   */
  @Test
  void addToCatalog() {
    // Arrange
    CatalogBook.Barcode catalogNumber = new CatalogBook.Barcode("Barcode");
    CatalogBook catalogBook = new CatalogBook("Dr", catalogNumber, "Isbn",
        new CatalogBook.Author("Name"));

    when(catalogRepository.save(Mockito.<CatalogBook>any())).thenReturn(catalogBook);

    // Act
    CatalogBook actualAddToCatalogResult = catalogManagementCommand.addToCatalog("Dr",
        new CatalogBook.Barcode("Barcode"),
        "Isbn", "JaneDoe");

    // Assert
    verify(catalogRepository).save(isA(CatalogBook.class));
    assertSame(catalogBook, actualAddToCatalogResult);
  }

}
