package bedu.org.BudgetCalculator.dto.Budget;


import bedu.org.BudgetCalculator.model.Client;
import bedu.org.BudgetCalculator.model.Estatus;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;


@Data
public class CreateBudgetDTO {

    @NotBlank(message = "El campo nombre presupuesto es obligatorio")
    private String nameBudget;
    @NotNull(message = "El id del cliente es obligatorio.")
    private Client customerId;
    @PositiveOrZero(message = "El total debe ser positivo")
    @DecimalMin(value = "1.0000",message = "El total debe ser mayor a 0")
    private double total;

    @NotNull(message = "La feha inicio es obligatoria")
    @FutureOrPresent(message = "La fecha inicio debe ser igual o mayor a hoy.")
    private LocalDate startDate;
    @NotNull(message = "La fecha fin es obligatoria")
    @Future(message = "La fecha fin debe ser mayor a hoy")
    private LocalDate endDate;
    @NotNull(message = "Es campo Estado es obligatorio")
    private Estatus status;
    private boolean Active;
    private boolean Generated;
    private boolean Accepted;

}
