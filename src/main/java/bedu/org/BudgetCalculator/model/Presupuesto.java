package bedu.org.BudgetCalculator.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
@Getter
@Setter
@ToString
@Entity
@Table(name="presupuestos")
public class Presupuesto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nombre_presupuesto")
    private String nombre;

   /* private List<Concepto> concepto; //Debe llenarse con model de concepto
    @Column(name = "conceptos")
    private List<Long> conceptoId;
    */
    @Column(nullable = false)
    private double total;
    @Column(nullable = false)
    private LocalDateTime fecha_creacion;
    @Column(nullable = false)
    private LocalDate fecha_inicio;
    @Column(nullable = false)
    private LocalDate fecha_fin;
    @Column(nullable = false)
    private Estatus estado; // llenarse con un model de estados

    private boolean isActivo;
    private boolean isGenerado;
    private boolean isAceptado;



}
