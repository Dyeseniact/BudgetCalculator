package bedu.org.budget_calculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bedu.org.budget_calculator.model.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
