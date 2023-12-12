package bedu.org.BudgetCalculator.dto.Budget;

import bedu.org.BudgetCalculator.model.Client;
import bedu.org.BudgetCalculator.model.Estatus;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateBudgetDTO {
    private String nameBudget;
    private Client customerId;
    private double total;
    @FutureOrPresent(message = "La fecha inicio debe ser mayor o igual a hoy.")
    private LocalDate startDate;
    @Future(message = "La fecha fin debe ser mayor a hoy")
    private LocalDate endDate;
    private Estatus status;
    private boolean Active;
    private boolean Generated;
    private boolean Accepted;
}
