package bedu.org.BudgetCalculator.dto.Customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CreateCustomerDTO {
    @NotBlank
    private String name;

    @NotBlank
    private String apellido;

    @NotBlank
    @Pattern(regexp = "\\d{10}", message = "El número de teléfono debe tener 10 dígitos")
    private String telefono;

    @NotBlank
    @Email
    private String email;
}
