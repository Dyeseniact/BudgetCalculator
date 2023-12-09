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
@Table(name="concepts")
public class Concept {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "budget_id", referencedColumnName = "id", nullable = false)
    private Budget budgetId;
    @ManyToOne
    @JoinColumn(name = "activity_id", referencedColumnName = "id")
    private Activity activityId;
    @ManyToOne
    @JoinColumn(name = "material_Id", referencedColumnName = "id")
    private Material materialId ;

    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private double quantity;
    @Column(nullable = false)
    private double unitPrice;
    @Column(nullable = false)
    private double subtotal;
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    @Column(name ="end_date" , nullable = false)
    private LocalDate endDate;

}
