package bedu.org.BudgetCalculator.dto.Presupuesto;

import bedu.org.BudgetCalculator.model.Customer;
import bedu.org.BudgetCalculator.model.Estatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
public class PresupuestoDTO {
    private long id;
    private String nombre;
    private Customer clienteid;
    private double total;
    private LocalDateTime fecha_creacion;
    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;
    private Estatus estado;
    private boolean Activo;
    private boolean Generado;
    private boolean Aceptado;


}
