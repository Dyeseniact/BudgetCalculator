package bedu.org.BudgetCalculator.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import bedu.org.BudgetCalculator.dto.ClienteDTO;
import bedu.org.BudgetCalculator.dto.CreateClienteDTO;
import bedu.org.BudgetCalculator.model.Cliente;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ClienteMapper {

    ClienteDTO toDTO(Cliente model);

    @Mapping(target = "id", ignore = true)
    Cliente toModel(CreateClienteDTO dto);

}
