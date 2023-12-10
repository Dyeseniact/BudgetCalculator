package bedu.org.BudgetCalculator.dto.activity;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateActivityDTO {

    @NotBlank
    private String name;
    @NotBlank
    private String unit;
}
