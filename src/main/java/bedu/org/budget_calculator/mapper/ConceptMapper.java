package bedu.org.budget_calculator.mapper;

import org.mapstruct.*;

import bedu.org.budget_calculator.dto.concept.ConceptDTO;
import bedu.org.budget_calculator.dto.concept.CreateConceptDTO;
import bedu.org.budget_calculator.dto.concept.UpdateConceptDTO;
import bedu.org.budget_calculator.model.Concept;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ConceptMapper {
    ConceptDTO toDTO(Concept model);
    @Mapping(target = "id", ignore = true)
    Concept toModel(CreateConceptDTO DTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "budgetId",ignore = true)
    @Mapping(target = "activityId", ignore = true)
    @Mapping(target = "materialId", ignore = true)
    void update(@MappingTarget Concept concept, UpdateConceptDTO data);

    List<ConceptDTO> toDTO(List<Concept> model);
}
