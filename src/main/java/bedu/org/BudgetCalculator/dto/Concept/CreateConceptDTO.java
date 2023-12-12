package bedu.org.BudgetCalculator.dto.Concept;

import bedu.org.BudgetCalculator.model.Activity;
import bedu.org.BudgetCalculator.model.Budget;
import bedu.org.BudgetCalculator.model.Material;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateConceptDTO {
    @NotNull
    private Budget budgetId;

    private Activity activityId;
    private Material materialId;

    @NotBlank(message = "La decripción es obligatoria")
    private String description;
    @DecimalMin(value = "0.01", message = "La cantidad minima bebe ser igual o mayor a 0.01")
    private double quantity;
    @DecimalMin(value = "0.01", message = "El precio minimo debe ser igual o mayor a 0.01 centavo")
    private double unitPrice;
    private double subtotal;
    @NotNull(message = "La fecha inicio es obligatoria")
    @FutureOrPresent(message = "La fecha debe ser igual o mayor a hoy")
    private LocalDate startDate;
    @NotNull(message = "La fecha fin es obligatoria")
    @Future(message = "La fecha debe ser  mayor a hoy")
    private LocalDate endDate;
}
