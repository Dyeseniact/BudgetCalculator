package bedu.org.BudgetCalculator.mappers.Presupuesto;

import bedu.org.BudgetCalculator.dto.Presupuesto.CreatePresupuestoDTO;
import bedu.org.BudgetCalculator.dto.Presupuesto.PresupuestoDTO;
import bedu.org.BudgetCalculator.model.Presupuesto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PresupuestoMapper {
    PresupuestoDTO toDTO(Presupuesto model);

    @Mapping(target = "id",ignore = true)
    Presupuesto toModel(CreatePresupuestoDTO DTO);



}
