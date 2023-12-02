package bedu.org.BudgetCalculator.dto.Concepto;

import bedu.org.BudgetCalculator.model.Presupuesto;
import lombok.Data;


import java.time.LocalDate;
@Data
public class ConceptoDTO {
    private long id;

    private Presupuesto presupuestoId;
    /*

    private Long actividadId;

    private Long productoId ;
*/

    private String concepto; //Debe llenarse con model de actividad

    private double cantidad;

    private double precioUnit;

    private double subtotal;

    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;
}
