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
    @NotBlank
    private String name;

    @Schema(description = "Client lastname", example = "Cuevas"
    )
    @NotBlank
    private String lastname;

    @Schema(description = "Client phone", example = "5500342356"
    )
    @NotBlank
    @Pattern(regexp = "\\d{10}", message = "The phone must be 10 digits long")
    private String phone;

    @Schema(description = "Client email", example = "jose@yahoo.com"
    )
    @NotBlank
    @Email
    private String email;
}
