package bedu.org.budget_calculator.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bedu.org.budget_calculator.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByNameContaining(String name);
}
