package bedu.org.BudgetCalculator.dto.Presupuesto;

import bedu.org.BudgetCalculator.model.Concepto;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CreatePresupuestoDTO {

    @NotBlank(message = "El campo nombre no puede estar vació, revisar el dato")
    private String nombre;
    //@NotEmpty(message = "Se debe colocar minimo una actividad")
    //private List<Concepto> concepto; //Debe llenarse con model de concepto
    private long conceptoId;
    @PositiveOrZero(message = "El total debe ser positivo")
    @DecimalMin(value = "1.0000",message = "El total debe ser mayor a 0")
    private double total;
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime fecha_creacion;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate fecha_inicio;
    /*
    @DateTimeFormat
    private LocalDate fecha_fin;
    */
    @Min(value = 1, message = "Se debe seleccioanr un Estado")
    private int estado; // llenarse con un model de estados

    /*

    private boolean isActivo;
    private boolean isGenerado;
    private boolean isAceptado;

*/


}
