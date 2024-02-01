package bedu.org.budget_calculator.dto.client;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ClientDTO {

    @Schema(description = "Client ID", example = "16"
    )
    private long id;

    @Schema(description = "Client name", example = "José"
    )
    private String name;

    @Schema(description = "Client lastname", example = "Cuevas"
    )
    private String lastname;

    @Schema(description = "Client phone", example = "5500342356"
    )
    private String phone;

    @Schema(description = "Client email", example = "jose@yahoo.com"
    )
    private String email;
}
