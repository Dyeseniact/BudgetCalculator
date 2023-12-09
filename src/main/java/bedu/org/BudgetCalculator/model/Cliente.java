package bedu.org.BudgetCalculator.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "Clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
