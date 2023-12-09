package bedu.org.BudgetCalculator.dto.Concept;

import bedu.org.BudgetCalculator.model.Activity;
import bedu.org.BudgetCalculator.model.Material;
import bedu.org.BudgetCalculator.model.Budget;
import lombok.Data;


import java.time.LocalDate;
@Data
public class ConceptDTO {
    private long id;
    private Budget budgetId;
    private Activity activityId;
    private Material materialId ;
    private String description;
    private double quantity;
    private double unitPrice;
    private double subtotal;
    private LocalDate startDate;
    private LocalDate endDate;
}
