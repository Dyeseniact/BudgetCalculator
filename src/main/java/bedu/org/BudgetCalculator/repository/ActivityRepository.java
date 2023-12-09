package bedu.org.BudgetCalculator.repository;

import bedu.org.BudgetCalculator.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
