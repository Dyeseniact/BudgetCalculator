package bedu.org.BudgetCalculator.dto.Budget;

import bedu.org.BudgetCalculator.model.Client;
import bedu.org.BudgetCalculator.model.Estatus;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UpdateBudgetDTO {
    @NotBlank(message = "El campo nombre no puede estar vació, revisar el dato")
    private String nameBudget;
    @NotNull
    private Client customerId;
    @PositiveOrZero(message = "El total debe ser positivo")
    @DecimalMin(value = "1.0000",message = "El total debe ser mayor a 0")
    private double total;
    @NotNull
    @PastOrPresent
    private LocalDateTime creationDate;
    @NotNull
    @FutureOrPresent
    private LocalDate startDate;
    @NotNull
    @Future
    private LocalDate endDate;
    @NotNull
    private Estatus status;
    @NotNull
    private boolean Active;
    @NotNull
    private boolean Generated;
    @NotNull
    private boolean Accepted;
}
