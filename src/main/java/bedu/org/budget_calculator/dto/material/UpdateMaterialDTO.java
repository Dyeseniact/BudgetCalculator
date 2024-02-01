package bedu.org.budget_calculator.dto.material;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
@Data
public class UpdateMaterialDTO {
    @Schema(description = "type of material", example = "electric cable"
    )
    private String name;

@Schema(description = "quantity of material", example = "10"
    )
    private int quantity;

    @Schema(description = "price of material", example = "100"
    )
    private double price;
}
