package bedu.org.budget_calculator.dto.budget;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

import bedu.org.budget_calculator.model.Client;
import bedu.org.budget_calculator.model.Estatus;


@Data
public class CreateBudgetDTO {

     @Schema(description = "Name of the budget", example = "Installation of security systems."
    )
     @NotBlank(message = "El campo nombre presupuesto es obligatorio")
     private String nameBudget;

    @Schema(description = "Customer ID", example = "70"
    )
    @NotNull(message = "El id del cliente es obligatorio.")
    private Client customerId;

    @Schema(description = "Total of the budget", example = "2500.00"
    )
    @PositiveOrZero(message = "El total debe ser positivo")
    private double total;

    @Schema(description = "Day the budget was created", example = "2024-01-01"
    )
    @NotNull(message = "La feha Inicio es obligatoria")
    @FutureOrPresent(message = "La fecha Inicio debe ser igual o mayor a hoy.")
    private LocalDate startDate;

    @Schema(description = "Day the activities end", example = "2024-04-04"
    )
    @NotNull(message = "La fecha Fin es obligatoria")
    @Future(message = "La fecha Fin debe ser mayor a hoy")
    private LocalDate endDate;

    @Schema(description = "Status of the budget", example = "ACTIVO"
    )
    @NotNull(message = "Es campo Estado es obligatorio")
    private Estatus status;

    @Schema(description = "If the budget is currently active", example = "true"
    )
    private boolean active;

    @Schema(description = "If the budget was generated", example = "true"
    )
    private boolean generated;

    @Schema(description = "If the budget was accepted", example = "true"
    )
    private boolean accepted;

}
