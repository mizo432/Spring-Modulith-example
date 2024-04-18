package undecided.demo.catalog.application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import undecided.demo.catalog.model.CatalogBook;
import undecided.demo.catalog.model.CatalogRepository;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class CatalogManagementQueryTest {

  @Autowired
  private CatalogManagementQuery catalogManagementQuery;

  @MockBean
  private CatalogRepository catalogRepository;

  /**
   * Method under test: {@link CatalogManagementQuery#findById(Long)}
   */
  @Test
  void findById() {
    // Arrange, Act and Assert
    assertFalse(catalogManagementQuery.findById(1L).isPresent());
    assertFalse(catalogManagementQuery.findById(2L).isPresent());
  }

  /**
   * Method under test: {@link CatalogManagementQuery#findAll()}
   */
  @Test
  void findAll() {
    // Arrange
    ArrayList<CatalogBook> catalogBookList = new ArrayList<>();
    when(catalogRepository.findAll()).thenReturn(catalogBookList);

    // Act
    List<CatalogBook> actualFindAllResult = catalogManagementQuery.findAll();

    // Assert
    verify(catalogRepository).findAll();
    assertTrue(actualFindAllResult.isEmpty());
    assertSame(catalogBookList, actualFindAllResult);
  }

}
