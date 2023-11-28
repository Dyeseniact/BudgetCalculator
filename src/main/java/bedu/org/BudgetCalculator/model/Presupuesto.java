package bedu.org.BudgetCalculator.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
@Data
@AllArgsConstructor
public class Presupuesto {
    private long id;
    private String nombre;
   // private List<Concepto> concepto; //Debe llenarse con model de concepto
    private long conceptoId;
    private double total;
    private LocalDateTime fecha_creacion;
    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;
    private int estado; // llenarse con un model de estados

    private boolean isActivo;
    private boolean isGenerado;
    private boolean isAceptado;



}
