package bedu.org.BudgetCalculator.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import bedu.org.BudgetCalculator.dto.ClientDTO;
import bedu.org.BudgetCalculator.dto.CreateClientDTO;
import bedu.org.BudgetCalculator.model.Client;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ClientMapper {

    ClientDTO toDTO(Client model);

    @Mapping(target = "id", ignore = true)
    Client toModel(CreateClientDTO dto);

}
