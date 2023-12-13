package bedu.org.BudgetCalculator.dto.Budget;

import bedu.org.BudgetCalculator.model.Client;
import bedu.org.BudgetCalculator.model.Estatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
public class BudgetDTO {

    @Schema(description = "ID of the budget", example = "16"
    )
    private long id;

    @Schema(description = "Name of the budget", example = "Installation of security systems."
    )
    private String nameBudget;

    @Schema(description = "Customer ID", example = "70"
    )
    private Client customerId;

    @Schema(description = "Total of the budget", example = "$2,500"
    )
    private double total;

    @Schema(description = "Day the budget was created", example = "12/12/2023"
    )
    private LocalDateTime creationDate;

    @Schema(description = "Day activities begin", example = "16/12/2023"
    )
    private LocalDate startDate;

    @Schema(description = "Day the activities end", example = "10/01/2024"
    )
    private LocalDate endDate;

    @Schema(description = "Status of the budget", example = "created"
    )
    private Estatus status;

    @Schema(description = "If the budget is currently active", example = "true"
    )
    private boolean Active;

    @Schema(description = "If the budget was generated", example = "true"
    )
    private boolean Generated;

    @Schema(description = "iIf the budget was accepted", example = "true"
    )
    private boolean Accepted;


}
