package bedu.org.BudgetCalculator.dto;

import lombok.Data;

@Data
public class ClienteDTO {
    
    private long id;
    private String name;
    private String apellido;
    private String telefono;
    private String email;
}
