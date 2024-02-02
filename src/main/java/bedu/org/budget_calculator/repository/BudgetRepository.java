package bedu.org.budget_calculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bedu.org.budget_calculator.model.Budget;

import java.util.List;

@Repository
public interface BudgetRepository extends JpaRepository<Budget,Long> {

    @Query(value = "SELECT * FROM budgets b WHERE b.budget_name like %:data%",nativeQuery = true)
    List<Budget> findByBudgetNameContaining(String data);
    @Query(value = "SELECT * FROM budgets b WHERE b.customer_id = :customerId", nativeQuery = true)
    List<Budget> findByCustomerId(Long customerId);
}
