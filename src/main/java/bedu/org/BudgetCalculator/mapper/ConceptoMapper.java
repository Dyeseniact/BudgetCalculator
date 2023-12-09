package bedu.org.BudgetCalculator.mapper;

import bedu.org.BudgetCalculator.dto.Concepto.ConceptoDTO;
import bedu.org.BudgetCalculator.dto.Concepto.CreateConceptoDTO;
import bedu.org.BudgetCalculator.model.Concepto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ConceptoMapper {
    ConceptoDTO toDTO(Concepto model);
    @Mapping(target = "id", ignore = true)
    Concepto toModel(CreateConceptoDTO DTO);


}
