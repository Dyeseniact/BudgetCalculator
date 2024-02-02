package bedu.org.budget_calculator.repository;

import bedu.org.budget_calculator.model.Budget;
import bedu.org.budget_calculator.model.Client;
import bedu.org.budget_calculator.model.Estatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BudgetRepositoryTest {
    @MockBean
    BudgetRepository budgetRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    final LocalDate endDate = LocalDate.of(2024,02,22);
    final LocalDate startDate = LocalDate.of(2024,02,2);


    @Test
    @DisplayName("Repository should be injected")
    void smokeTest(){
        assertNotNull(budgetRepository);
    }

    @Test
    @DisplayName("BudgetRepository should filter Budgets by budgetName")
    void findByBudgetNameTest(){
        Budget budget1 = new Budget();
        budget1.setNameBudget("Busqueda por nombre 1");

        Budget budget2 = new Budget();
        budget2.setNameBudget("Busqueda por nombre 2");

        List<Budget> fakeList = new LinkedList<>();
        fakeList.add(budget1);
        fakeList.add(budget2);

        when(budgetRepository.findByBudgetNameContaining(anyString())).thenReturn(fakeList);

        List<Budget> result = budgetRepository.findByBudgetNameContaining("nombre");
        assertTrue(result.size()==2);
        assertEquals(budget1.getNameBudget(),result.get(0).getNameBudget());
        assertEquals(budget2.getNameBudget(),result.get(1).getNameBudget());

    }

}