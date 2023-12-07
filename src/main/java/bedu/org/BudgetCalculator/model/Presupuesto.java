package bedu.org.BudgetCalculator.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
@ToString
@Entity
@Table(name="presupuestos")
public class Presupuesto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nombre_presupuesto", nullable = false)
    private String nombre;
    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id", nullable = false)
    private Customer clienteid;
    @Column(nullable = false)
    private double total;
    @Column(name = "fecha_creacion",nullable = false)
    private LocalDateTime fecha_creacion;
    @Column(nullable = false)
    private LocalDate fecha_inicio;
    @Column(nullable = false)
    private LocalDate fecha_fin;
    @Column(nullable = false)
    private Estatus estado; // llenarse con un model de estados

    private boolean Activo;
    private boolean Generado;
    private boolean Aceptado;



}
