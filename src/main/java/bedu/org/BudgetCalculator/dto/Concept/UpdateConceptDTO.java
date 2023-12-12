package bedu.org.BudgetCalculator.dto.Concept;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
@Data
public class UpdateConceptDTO {
    private String description;
    private double quantity;
    private double unitPrice;
    private double subtotal;

    @FutureOrPresent(message = "La fecha inicio debe ser igual o mayor a hoy")
    private LocalDate startDate;

    @Future(message = "La fecha fin debe ser  mayor a hoy")
    private LocalDate endDate;
}
