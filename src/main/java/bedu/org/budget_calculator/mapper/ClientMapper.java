package bedu.org.budget_calculator.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import bedu.org.budget_calculator.dto.client.ClientDTO;
import bedu.org.budget_calculator.dto.client.CreateClientDTO;
import bedu.org.budget_calculator.model.Client;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ClientMapper {

    ClientDTO toDTO(Client model);

    @Mapping(target = "id", ignore = true)
    Client toModel(CreateClientDTO dto);

}
