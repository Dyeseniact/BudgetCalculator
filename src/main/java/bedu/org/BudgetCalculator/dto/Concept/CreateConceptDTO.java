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

    @NotBlank
    private String description; //Debe llenarse con model de actividad
    @DecimalMin(value = "0.01", message = "La cantidad minima bebe ser igual o mayor a 0.01")
    private double quantity;
    @DecimalMin(value = "0.01", message = "El precio minimo debe ser igual o mayor a 0.01 centavo")
    private double unitPrice;
    //@PositiveOrZero(message = "El subtotal debe ser mayor o igual a 0")
    //@DecimalMin(value = "0.01", message = "El subtotal debe ser mayor a 0.01 centavo")
    private double subtotal;
    @NotNull
    @FutureOrPresent(message = "La fecha debe ser igual o mayor a hoy")
    private LocalDate startDate;
    @NotNull
    @Future(message = "La fecha debe ser  mayor a hoy")
    private LocalDate endDate;
}
