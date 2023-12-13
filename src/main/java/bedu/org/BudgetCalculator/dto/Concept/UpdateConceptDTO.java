package bedu.org.BudgetCalculator.dto.Concept;

import jakarta.validation.constraints.*;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
@Data
public class UpdateConceptDTO {

    @Schema(description = "Concept description ", example = "Installation of wooden floors."
    )
    private String description;

    @Schema(description = "Concept quantity", example = "3"
    )
    private double quantity;

    @Schema(description = "Concept unitprice ", example = "1000"
    )
    private double unitPrice;

    @Schema(description = "Concept subtotal ", example = "3000"
    )
    private double subtotal;

    @Schema(description = "Day activities begin", example = "16/12/2023"
    )
    @FutureOrPresent(message = "La fecha inicio debe ser igual o mayor a hoy")
    private LocalDate startDate;

    @Schema(description = "Day the activities end", example = "10/01/2024"
    )
    @Future(message = "La fecha fin debe ser  mayor a hoy")
    private LocalDate endDate;
}
