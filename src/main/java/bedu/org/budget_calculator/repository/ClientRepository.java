package bedu.org.budget_calculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bedu.org.budget_calculator.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
