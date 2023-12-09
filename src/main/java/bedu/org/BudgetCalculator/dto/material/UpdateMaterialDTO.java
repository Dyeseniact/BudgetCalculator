package bedu.org.BudgetCalculator.dto.material;

import lombok.Data;
@Data
public class UpdateMaterialDTO {
    private String name;

    private int quantity;

    private double price;
}
