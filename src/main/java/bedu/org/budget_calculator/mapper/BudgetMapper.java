package bedu.org.budget_calculator.mapper;

import org.mapstruct.*;

import bedu.org.budget_calculator.dto.budget.BudgetDTO;
import bedu.org.budget_calculator.dto.budget.CreateBudgetDTO;
import bedu.org.budget_calculator.dto.budget.UpdateBudgetDTO;
import bedu.org.budget_calculator.model.Budget;

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
