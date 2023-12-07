package bedu.org.BudgetCalculator.model;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Entity
@Table(name="conceptos")
public class Concepto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "presupuesto_id", referencedColumnName = "id", nullable = false)
    private Presupuesto presupuestoId;

    @ManyToOne
    @JoinColumn(name = "activity_id", referencedColumnName = "id")
    private Activity activityId;

/*    @ManyToMany
    @JoinColumn(name = "presupuesto_fk", referencedColumnName = "id")
    private Long productoId ;
*/

    @Column(name = "descripcion",nullable = false)
    private String description;
    @Column(nullable = false)
    private double cantidad;

    @Column(nullable = false)
    private double precioUnit;

    private double subtotal;
    @Column(nullable = false)
    private LocalDate fecha_inicio;
    @Column(nullable = false)
    private LocalDate fecha_fin;

}
