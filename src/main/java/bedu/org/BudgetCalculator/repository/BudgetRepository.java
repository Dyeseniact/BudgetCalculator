package bedu.org.BudgetCalculator.repository;

import bedu.org.BudgetCalculator.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends JpaRepository<Budget,Long> {


}
