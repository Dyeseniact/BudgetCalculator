package bedu.org.BudgetCalculator.repository;

import bedu.org.BudgetCalculator.model.Presupuesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Repository
public interface PresupuestoRepository extends JpaRepository<Presupuesto,Long> {
   // List<Presupuesto> findAll();

}
