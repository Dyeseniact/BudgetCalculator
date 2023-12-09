package bedu.org.BudgetCalculator.dto;

import lombok.Data;

@Data
public class MaterialDTO {
    private long id;
    private String name;
    private int quantity;
    private double price;
}
