package undecided.demo.catalog.infrastructure.api;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import undecided.demo.catalog.domain.CatalogBook;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookMapper {

  BookDto toDto(CatalogBook catalogBook);

  CatalogBook toEntity(BookDto bookDto);
}
