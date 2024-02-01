package bedu.org.budget_calculator.dto.activity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UpdateActivityDTO {

        @Schema(description = "name of the activity to be updated", example = "mosaic application on the floor"
        )
        private String name;

        @Schema(description = "Unit of the activity", example = "1"
        )
        private String unit;
}
