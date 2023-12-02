package bedu.org.BudgetCalculator.dto.Presupuesto;

import bedu.org.BudgetCalculator.model.Concepto;
import bedu.org.BudgetCalculator.model.Estatus;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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
    private boolean isActivo;
    private boolean isGenerado;
    private boolean isAceptado;


}
