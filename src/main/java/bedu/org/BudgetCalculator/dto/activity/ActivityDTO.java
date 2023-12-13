package bedu.org.BudgetCalculator.dto.activity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ActivityDTO {

    @Schema(description = "ID of the activity to be updated", example = "10"
    )
    private long id;

    @Schema(description = "name of the activity", example = "painted wall"
    )
    private String name;

    @Schema(description = "unit of the activity", example = "5"
    )
    private String unit;
}
