package bedu.org.BudgetCalculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bedu.org.BudgetCalculator.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
