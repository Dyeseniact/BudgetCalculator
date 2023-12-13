package bedu.org.BudgetCalculator.dto.Concept;

import bedu.org.BudgetCalculator.model.Activity;
import bedu.org.BudgetCalculator.model.Material;
import bedu.org.BudgetCalculator.model.Budget;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


import java.time.LocalDate;
@Data
public class ConceptDTO {
    @Schema(description = "Concept ID", example = "20"
    )
    private long id;


    private Budget budgetId;
    private Activity activityId;
    private Material materialId ;

    @Schema(description = "Concept description ", example = "Installation of wooden floors."
    )
    private String description;

    @Schema(description = "Concept quantity", example = "3"
    )
    private double quantity;

    @Schema(description = "Concept unitprice ", example = "1000"
    )
    private double unitPrice;

    @Schema(description = "Concept subtotal ", example = "3000"
    )
    private double subtotal;

    @Schema(description = "Day activities begin", example = "16/12/2023"
    )
    private LocalDate startDate;

    @Schema(description = "Day the activities end", example = "10/01/2024"
    )
    private LocalDate endDate;
}
