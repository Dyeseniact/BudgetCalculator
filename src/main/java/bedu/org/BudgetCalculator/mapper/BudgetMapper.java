package bedu.org.BudgetCalculator.mapper;

import bedu.org.BudgetCalculator.dto.Budget.BudgetDTO;
import bedu.org.BudgetCalculator.dto.Budget.CreateBudgetDTO;
import bedu.org.BudgetCalculator.dto.Budget.UpdateBudgetDTO;
import bedu.org.BudgetCalculator.model.Budget;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BudgetMapper {
    BudgetDTO toDTO(Budget model);

    @Mapping(target = "id",ignore = true)
    Budget toModel(CreateBudgetDTO DTO);
    @Mapping(target = "id",ignore = true)
    void update(@MappingTarget Budget budget, UpdateBudgetDTO data);

}
