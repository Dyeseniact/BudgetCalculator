package bedu.org.BudgetCalculator.repository;

import bedu.org.BudgetCalculator.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
