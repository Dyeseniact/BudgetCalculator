package bedu.org.budget_calculator.repository;

import bedu.org.budget_calculator.model.Budget;
import bedu.org.budget_calculator.model.Client;
import bedu.org.budget_calculator.model.Concept;
import bedu.org.budget_calculator.model.Estatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ConceptRepositoryTest {
    @Autowired
    private ConceptRepository conceptRepository;
    @Autowired
    TestEntityManager testEntityManager;

    final LocalDate startDate = LocalDate.of(2024, 2, 8);
    final LocalDate endDate = LocalDate.of(2024, 2, 18);

    @Test
    @DisplayName("Injected")
    void smokeTest() {
        assertNotNull(conceptRepository);
    }

    @Test
    @DisplayName("Repository should filter concepts by Budget")
    void findsConceptsByBudgetId() {
        Concept concept1 = new Concept();
        Concept concept2 = new Concept();
        Concept concept3 = new Concept();

        Client client = new Client();
        client.setName("Erick");
        client.setLastname("Rios");
        client.setEmail("erick@gmail.com");
        client.setPhone("9632587410");

        Budget budget = new Budget();
        budget.setNameBudget("Pruebas test repo");
        budget.setCustomerId(client);
        budget.setStartDate(startDate);
        budget.setEndDate(endDate);
        budget.setTotal(125.00);
        budget.setStatus(Estatus.PENDIENTE);

        testEntityManager.persist(client);

        testEntityManager.persist(budget);


        concept1.setBudgetId(budget);
        concept1.setDescription("Concept 1 Test");
        concept1.setQuantity(10);
        concept1.setActivityId(null);
        concept1.setUnitPrice(150);
        concept1.setStartDate(startDate);
        concept1.setEndDate(endDate);

        concept2.setBudgetId(budget);
        concept2.setDescription("Concept 2 Test");
        concept2.setQuantity(10);
        concept2.setActivityId(null);
        concept2.setUnitPrice(150);
        concept2.setStartDate(startDate);
        concept2.setEndDate(endDate);

        concept3.setBudgetId(budget);
        concept3.setDescription("Concept 3 Test");
        concept3.setQuantity(10);
        concept3.setActivityId(null);
        concept3.setUnitPrice(150);
        concept3.setStartDate(startDate);
        concept3.setEndDate(endDate);


        testEntityManager.persist(concept1);
        testEntityManager.persist(concept2);
        testEntityManager.persist(concept3);

        List<Concept> result = conceptRepository.findsConceptsByBudgetId(budget.getId());

        assertEquals( 3,result.size());

    }
}