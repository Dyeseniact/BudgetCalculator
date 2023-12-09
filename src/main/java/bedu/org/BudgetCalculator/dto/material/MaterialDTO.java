package bedu.org.BudgetCalculator.dto.material;

import lombok.Data;

@Data
public class MaterialDTO {
    private long id;
    private String name;
    private int quantity;
    private double price;
}
