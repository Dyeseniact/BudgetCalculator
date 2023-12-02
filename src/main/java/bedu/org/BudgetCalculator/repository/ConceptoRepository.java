package bedu.org.BudgetCalculator.repository;

import bedu.org.BudgetCalculator.model.Concepto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConceptoRepository extends CrudRepository<Concepto,Long> {
    List<Concepto> findAll();
}
