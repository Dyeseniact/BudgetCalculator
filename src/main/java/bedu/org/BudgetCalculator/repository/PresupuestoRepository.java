package bedu.org.BudgetCalculator.repository;

import bedu.org.BudgetCalculator.model.Presupuesto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Repository
public interface PresupuestoRepository extends CrudRepository<Presupuesto,Long> {
    List<Presupuesto> findAll();

}
