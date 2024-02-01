package bedu.org.BudgetCalculator.repository;

import bedu.org.BudgetCalculator.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {

    List<Material> findByNameContaining(String name);

}
