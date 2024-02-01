package bedu.org.budget_calculator.dto.concept;

import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

import bedu.org.budget_calculator.model.Activity;
import bedu.org.budget_calculator.model.Budget;
import bedu.org.budget_calculator.model.Material;

@Data
public class CreateConceptDTO {
    @NotNull
    private Budget budgetId;

    private Activity activityId;
    private Material materialId;
    @Schema(description = "Concept description ", example = "Installation of wooden floors."
    )
    @NotBlank(message = "La decripción es obligatoria")
    private String description;

    @Schema(description = "Concept quantity", example = "3"
    )
    @DecimalMin(value = "0.01", message = "La cantidad minima bebe ser igual o mayor a 0.01")
    private double quantity;

    @Schema(description = "Concept unitprice ", example = "1000"
    )
    @DecimalMin(value = "0.01", message = "El precio minimo debe ser igual o mayor a 0.01 centavo")
    private double unitPrice;

    @Schema(description = "Concept subtotal ", example = "3000"
    )
    private double subtotal;

    @Schema(description = "Day activities begin", example = "16/12/2023"
    )
    @NotNull(message = "La fecha inicio es obligatoria")
    @FutureOrPresent(message = "La fecha debe ser igual o mayor a hoy")
    private LocalDate startDate;

    @Schema(description = "Day the activities end", example = "10/01/2024"
    )
    @NotNull(message = "La fecha fin es obligatoria")
    @Future(message = "La fecha debe ser  mayor a hoy")
    private LocalDate endDate;
}
