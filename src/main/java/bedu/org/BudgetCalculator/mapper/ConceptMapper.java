package bedu.org.BudgetCalculator.mapper;

import bedu.org.BudgetCalculator.dto.Concept.ConceptDTO;
import bedu.org.BudgetCalculator.dto.Concept.CreateConceptDTO;
import bedu.org.BudgetCalculator.dto.Concept.UpdateConceptDTO;
import bedu.org.BudgetCalculator.model.Concept;
import org.mapstruct.*;

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
