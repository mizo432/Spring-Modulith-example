package undecided.demo.catalog.application;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import undecided.demo.catalog.model.CatalogBook;
import undecided.demo.catalog.model.CatalogRepository;

/**
 * The CatalogManagementQuery class is responsible for querying the book catalog.
 */
@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class CatalogManagementQuery {

  private final CatalogRepository catalogRepository;

  /**
   * Retrieves a catalog book from the repository based on its ID.
   *
   * @param id the ID of the book
   * @return an Optional containing the catalog book, or an empty Optional if not found
   */
  @Transactional(readOnly = true)
  public Optional<CatalogBook> findById(@NonNull Long id) {
    return catalogRepository.findById(id);

  }


  /**
   * Retrieves all catalog books from the repository.
   *
   * @return a List of CatalogBook objects representing all the catalog books
   */
  @Transactional(readOnly = true)
  public List<CatalogBook> findAll() {
    return catalogRepository.findAll();
  }
}
