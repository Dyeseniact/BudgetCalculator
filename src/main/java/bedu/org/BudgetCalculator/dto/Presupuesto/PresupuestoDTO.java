package bedu.org.BudgetCalculator.dto.Presupuesto;

import bedu.org.BudgetCalculator.model.Estatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
public class PresupuestoDTO {
    private long id;
    private String nombre;
   // private List<Long> conceptoId;
    private double total;
    private LocalDateTime fecha_creacion;
    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;
    private Estatus estado; // llenarse con un model de estados
    private boolean Activo;
    private boolean Generado;
    private boolean Aceptado;


}
