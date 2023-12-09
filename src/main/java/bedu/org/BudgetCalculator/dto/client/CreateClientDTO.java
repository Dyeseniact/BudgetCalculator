package bedu.org.BudgetCalculator.dto.client;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CreateClientDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String lastname;

    @NotBlank
    @Pattern(regexp = "\\d{10}", message = "The phone must be 10 digits long")
    private String phone;

    @NotBlank
    @Email
    private String email;
}
