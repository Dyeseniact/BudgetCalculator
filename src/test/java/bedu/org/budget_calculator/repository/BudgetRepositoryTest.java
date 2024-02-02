package bedu.org.budget_calculator.repository;

import bedu.org.budget_calculator.model.Budget;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;

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
    private TestEntityManager manager;

    @Test
    @DisplayName("budgetRepository should be injected")
    void smokeTest(){
        assertNotNull(budgetRepository);
    }

    @Test
    @DisplayName("budgetRepository should filter Budgets by budgetName")
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
        assertEquals(2,result.size());
        assertEquals(budget1.getNameBudget(),result.get(0).getNameBudget());
        assertEquals(budget2.getNameBudget(),result.get(1).getNameBudget());

    }

}