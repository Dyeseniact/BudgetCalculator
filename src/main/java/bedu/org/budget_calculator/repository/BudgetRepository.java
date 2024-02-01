package bedu.org.budget_calculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bedu.org.budget_calculator.model.Budget;

@Repository
public interface BudgetRepository extends JpaRepository<Budget,Long> {


}
