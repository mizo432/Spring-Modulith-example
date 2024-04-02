package undecided.demo.catalog.application;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import undecided.demo.catalog.domain.CatalogBook;
import undecided.demo.catalog.domain.CatalogRepository;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class CatalogManagementQuery {

  private final CatalogRepository catalogRepository;

  @Transactional(readOnly = true)
  public Optional<CatalogBook> locate(Long id) {
    return catalogRepository.findById(id);

  }


  @Transactional(readOnly = true)
  public List<CatalogBook> fetchBooks() {
    return catalogRepository.findAll();
  }
}
