package bedu.org.BudgetCalculator.dto.Customer;

import lombok.Data;

@Data
public class CustomerDTO {
    private long id;
    private String name;
    private String apellido;
    private String telefono;
    private String email;

}
