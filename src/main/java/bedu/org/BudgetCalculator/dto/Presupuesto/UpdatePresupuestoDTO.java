package bedu.org.BudgetCalculator.dto.Presupuesto;

import bedu.org.BudgetCalculator.model.Estatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UpdatePresupuestoDTO {
    @NotNull
    private long id;
    @NotBlank(message = "El campo nombre no puede estar vació, revisar el dato")
    private String nombre;
    @DecimalMin(value = "1.0000", message = "El total debe ser mayor a 0.0")
    private double total;
    private LocalDateTime fecha_creacion;
    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;
    @NotNull(message = "Se debe seleccionar un estado")
    private Estatus estado;
    private boolean Activo;
    private boolean Generado;
    private boolean Aceptado;
}
