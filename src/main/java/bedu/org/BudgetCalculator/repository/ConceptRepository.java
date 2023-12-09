package bedu.org.BudgetCalculator.repository;

import bedu.org.BudgetCalculator.model.Concept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConceptRepository extends JpaRepository<Concept,Long> {
   // List<Concepto> findAll();
    @Query(value = "SELECT * FROM conceptos c WHERE c.presupuesto_id= :presupuestoId",nativeQuery = true)
    List<Concept> findsConceptsByPresupuesto(Long presupuestoId);
}
