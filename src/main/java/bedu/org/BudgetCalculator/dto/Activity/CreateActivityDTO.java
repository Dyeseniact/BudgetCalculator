package bedu.org.BudgetCalculator.dto.Activity;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateActivityDTO {

    @NotBlank
    private String name;
    @NotBlank
    private String unit;
}
