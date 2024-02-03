package bedu.org.budget_calculator.repository;

import bedu.org.budget_calculator.model.Material;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MaterialRepositoryTest {
    @Autowired
    private MaterialRepository repository;

    @Autowired
    private TestEntityManager manager;

    @Test
    @DisplayName("Repository should be injected")
    void smokeTest() {
        assertNotNull(repository);
    }

    @Test
    @DisplayName("Repository should filter materials by name")
    void findByNameTest() {
        Material material1 = new Material();
        material1.setName("Desarmador de cruz");
        material1.setQuantity(200);
        material1.setPrice(3.0);

        Material material2 = new Material();
        material2.setName("Desarmador de punta");
        material2.setQuantity(5000);
        material2.setPrice(1.0);

        Material material3 = new Material();
        material3.setName("Tornillo");
        material3.setQuantity(800);
        material3.setPrice(80.0);

        repository.save(material1);
        repository.save(material2);
        repository.save(material3);

        List<Material> result = repository.findByNameContaining("Desarmador");

        assertTrue(result.size() == 2);
    }
}
