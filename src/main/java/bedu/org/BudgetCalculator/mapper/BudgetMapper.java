package bedu.org.BudgetCalculator.mapper;

import bedu.org.BudgetCalculator.dto.Budget.BudgetDTO;
import bedu.org.BudgetCalculator.dto.Budget.CreateBudgetDTO;
import bedu.org.BudgetCalculator.dto.Budget.UpdateBudgetDTO;
import bedu.org.BudgetCalculator.model.Budget;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
public interface BudgetMapper {
    BudgetDTO toDTO(Budget model);

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    Budget toModel(CreateBudgetDTO DTO);
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    void update(@MappingTarget Budget budget, UpdateBudgetDTO data);

}
