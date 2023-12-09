package bedu.org.BudgetCalculator.dto.Concept;

import bedu.org.BudgetCalculator.model.Activity;
import bedu.org.BudgetCalculator.model.Presupuesto;
import lombok.Data;


import java.time.LocalDate;
@Data
public class ConceptDTO {
    private long id;
    private Presupuesto presupuestoId;
    private Activity activityId;
/*
    private Long productoId ;
*/
    private String description;
    private double cantidad;
    private double precioUnit;
    private double subtotal;
    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;
}
