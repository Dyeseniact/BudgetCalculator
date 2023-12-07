package bedu.org.BudgetCalculator.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "Clientes")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (nullable = false, length = 50)
    private String name;

    @Column (nullable = false, length = 50)
    private String apellido;

    @Column (nullable = false)
    private String telefono;

    @Column (nullable = false, length = 50)
    private String email;

}
