package bedu.org.BudgetCalculator.dto.material;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateMaterialDTO {
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "Name cannot be empty")
    @Min(value = 1, message = "Quantity must be greater than or equal to 1")
    private int quantity;

    @NotBlank(message = "Name cannot be empty")
    @DecimalMin(value = "0.0", message = "Price must be greater than or equal to 0.0")
    private double price;
}
