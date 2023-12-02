package bedu.org.BudgetCalculator.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bedu.org.BudgetCalculator.model.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {

    List<Cliente> findAll();
    
}
