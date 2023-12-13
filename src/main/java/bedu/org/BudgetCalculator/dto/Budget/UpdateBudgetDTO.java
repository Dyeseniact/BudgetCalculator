package bedu.org.BudgetCalculator.dto.Budget;

import bedu.org.BudgetCalculator.model.Client;
import bedu.org.BudgetCalculator.model.Estatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateBudgetDTO {
    @Schema(description = "Name of the budget", example = "Installation of security systems."
    )
    private String nameBudget;

    @Schema(description = " Customer ID", example = "60"
    )
    private Client customerId;

    @Schema(description = "Total of the budget", example = "27500.50"
    )
    @PositiveOrZero(message = "El total debe ser positivo")
    @DecimalMin(value = "1.0000",message = "El total debe ser mayor a 0")
    private double total;

    @Schema(description = "Day activities begin", example = "2023-12-12"
    )
    @FutureOrPresent(message = "La fecha inicio debe ser mayor o igual a hoy.")
    private LocalDate startDate;

    @Schema(description = "Day the activities end", example = "2024-04-04"
    )
    @Future(message = "La fecha fin debe ser mayor a hoy")
    private LocalDate endDate;

    @Schema(description = "Status of the budget", example = "ACTIVO"
    )
    private Estatus status;

    @Schema(description = "If the budget is currently active", example = "true"
    )
    private boolean Active;

    @Schema(description = "If the budget was generated", example = "true"
    )
    private boolean Generated;

    @Schema(description = "If the budget was accepted", example = "true"
    )
    private boolean Accepted;
}
