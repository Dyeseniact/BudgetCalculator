package bedu.org.BudgetCalculator.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @Column(name = "creation_date",nullable = false)
    private LocalDateTime creationDate;
    @Column(name ="start_date" , nullable = false)
    private LocalDate startDate;
    @Column(name ="end_date" , nullable = false)
    private LocalDate endDate;
    @Column(nullable = false)
    private Estatus status;

    private boolean Active;
    private boolean Generated;
    private boolean Accepted;



}
