package bedu.org.BudgetCalculator.dto.Budget;

import bedu.org.BudgetCalculator.model.Client;
import bedu.org.BudgetCalculator.model.Estatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
public class BudgetDTO {
    private long id;
    private String nameBudget;
    private Client customerId;
    private double total;
    private LocalDateTime creationDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private Estatus status;
    private boolean Active;
    private boolean Generated;
    private boolean Accepted;


}
