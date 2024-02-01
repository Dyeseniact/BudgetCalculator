package bedu.org.budget_calculator.dto.material;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateMaterialDTO {
    @Schema(description = "type of material", example = "electric cable"
    )
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Schema(description = "quantity of material", example = "10"
    )
    @Min(value = 1, message = "Quantity must be greater than or equal to 1")
    private int quantity;

    @Schema(description = "price of material", example = "100"
    )
    @DecimalMin(value = "0.0", message = "Price must be greater than or equal to 0.0")
    private double price;
}
