package bedu.org.BudgetCalculator.dto.activity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateActivityDTO {

    @Schema(description = "Name of the activity", example = "changing the electric cable"
    )
    @NotBlank
    private String name;

    @Schema(description = "unit of the activity", example = "2")
    @NotBlank
    private String unit;
}
