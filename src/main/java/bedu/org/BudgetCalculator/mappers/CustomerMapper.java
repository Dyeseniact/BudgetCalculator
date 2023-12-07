package bedu.org.BudgetCalculator.mappers;

import bedu.org.BudgetCalculator.dto.Customer.CreateCustomerDTO;
import bedu.org.BudgetCalculator.dto.Customer.CustomerDTO;
import bedu.org.BudgetCalculator.model.Customer;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CustomerMapper {
    CustomerDTO toDTO(Customer model);

    @Mapping(target = "id", ignore = true)
    Customer toModel(CreateCustomerDTO dto);
}
