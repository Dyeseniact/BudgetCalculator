package bedu.org.BudgetCalculator.mapper;

import bedu.org.BudgetCalculator.dto.Concept.ConceptDTO;
import bedu.org.BudgetCalculator.dto.Concept.CreateConceptDTO;
import bedu.org.BudgetCalculator.dto.Concept.UpdateConceptDTO;
import bedu.org.BudgetCalculator.model.Concept;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ConceptMapper {
    ConceptDTO toDTO(Concept model);
    @Mapping(target = "id", ignore = true)
    Concept toModel(CreateConceptDTO DTO);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Concept concept, UpdateConceptDTO data);

    List<ConceptDTO> toDTO(List<Concept> model);
}
