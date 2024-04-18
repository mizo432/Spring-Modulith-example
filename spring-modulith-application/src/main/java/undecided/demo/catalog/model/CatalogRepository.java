package undecided.demo.catalog.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogRepository extends JpaRepository<CatalogBook, Long> {

}
