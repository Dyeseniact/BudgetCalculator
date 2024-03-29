package bedu.org.budget_calculator.dto.client;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CreateClientDTO {
    @Schema(description = "Client name", example = "José"
    )
    @NotBlank(message = "Name must not be blank")
    private String name;

    @Schema(description = "Client lastname", example = "Cuevas"
    )
    @NotBlank(message = "Lastname must not be blank")
    private String lastname;

    @Schema(description = "Client phone", example = "5500342356"
    )
    @NotBlank(message = "Phone must not be blank")
    @Pattern(regexp = "\\d{10}", message = "The phone must be 10 digits long")
    private String phone;

    @Schema(description = "Client email", example = "jose@yahoo.com"
    )
    @NotBlank(message = "Email must not be blank")
    @Email
    private String email;
}
