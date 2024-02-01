package bedu.org.budget_calculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bedu.org.budget_calculator.model.Concept;

import java.util.List;

@Repository
public interface ConceptRepository extends JpaRepository<Concept,Long> {

    @Query(value = "SELECT * FROM concepts c WHERE c.budget_id= :budgetId",nativeQuery = true)
    List<Concept> findsConceptsByBudgetId(Long budgetId);
}
