package bedu.org.budget_calculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bedu.org.budget_calculator.model.Activity;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    List<Activity> findByNameContaining(String name);
}
