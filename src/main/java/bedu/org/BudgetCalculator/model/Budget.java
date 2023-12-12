package bedu.org.BudgetCalculator.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;

import java.time.Instant;
import java.time.LocalDate;
@Getter
@Setter
@ToString
@Entity
@Table(name="budgets")
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name_budget", nullable = false)
    private String nameBudget;
    @ManyToOne

    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private Client customerId;

    @Column(nullable = false)
    private double total;
    @CreationTimestamp(source = SourceType.DB)
    @Column(name = "creation_date")
    private Instant creationDate;
    @Column(name ="start_date" , nullable = false)
    private LocalDate startDate;
    @Column(name ="end_date" , nullable = false)
    private LocalDate endDate;
    @Column(nullable = false)
    private Estatus status;
    @Column(name = "is_Active")
    private boolean Active;
    @Column(name = "is_Generated")
    private boolean Generated;
    @Column(name = "is_Accepted")
    private boolean Accepted;



}
