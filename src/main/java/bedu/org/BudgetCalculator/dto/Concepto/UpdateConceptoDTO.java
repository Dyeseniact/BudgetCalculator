package bedu.org.BudgetCalculator.dto.Concepto;

import bedu.org.BudgetCalculator.model.Presupuesto;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateConceptoDTO {
    @NotNull
    private long id;
    @NotNull
    private Presupuesto presupuestoId;
    /*

    private Long actividadId;

    private Long productoId ;
*/
    @NotBlank
    private String concepto; //Debe llenarse con model de actividad
    @DecimalMin(value = "0.01", message = "La cantidad minima es 0.01")
    private double cantidad;

    @DecimalMin(value = "0.01", message = "El precio inimo debe ser 0.01")
    private double precioUnit;

    //@PositiveOrZero(message = "El subtotal debe ser mayor o igual a 0")
    @DecimalMin(value = "0.001", message = "El subtotal debe ser mayor a 0.01")
    private double subtotal;
    @NotNull
    private LocalDate fecha_inicio;
    @NotNull
    private LocalDate fecha_fin;
}
