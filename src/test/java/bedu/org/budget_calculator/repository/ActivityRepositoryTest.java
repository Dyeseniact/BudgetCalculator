package bedu.org.budget_calculator.repository;

import bedu.org.budget_calculator.model.Activity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ActivityRepositoryTest {

    @Autowired
    private ActivityRepository repository;

    @Test
    @DisplayName("Repository should be injected")
    void smokeTest() {
        assertNotNull(repository);
    }

    @Test
    @DisplayName("Repository should filter activities by name")
    void findByNameTest() {
        Activity activity1 = new Activity();
        activity1.setName("changing the electric cable");
        activity1.setUnit("2");

        Activity activity2 = new Activity();
        activity2.setName("changing the electric cable");
        activity2.setUnit("2");

        Activity activity3 = new Activity();
        activity3.setName("changing the electric cable");
        activity3.setUnit("2");

        repository.save(activity1);
        repository.save(activity2);
        repository.save(activity3);

        List<Activity> result = repository.findByNameContaining("changing the electric cable");

        assertEquals(3, result.size());
    }


}
