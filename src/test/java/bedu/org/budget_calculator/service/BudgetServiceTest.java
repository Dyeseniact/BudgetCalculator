package bedu.org.budget_calculator.service;

import bedu.org.budget_calculator.dto.budget.BudgetDTO;
import bedu.org.budget_calculator.dto.budget.CreateBudgetDTO;
import bedu.org.budget_calculator.dto.budget.UpdateBudgetDTO;
import bedu.org.budget_calculator.dto.concept.ConceptDTO;
import bedu.org.budget_calculator.exception.budget.BudgetNotFoundException;
import bedu.org.budget_calculator.mapper.ConceptMapper;
import bedu.org.budget_calculator.model.Budget;
import bedu.org.budget_calculator.model.Concept;
import bedu.org.budget_calculator.model.Estatus;
import bedu.org.budget_calculator.repository.BudgetRepository;
import bedu.org.budget_calculator.repository.ConceptRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class BudgetServiceTest {
    @MockBean
    private BudgetRepository budgetRepository;
    @MockBean
    private ConceptRepository conceptRepository;
    @Autowired
    private BudgetService budgetService;
    @Autowired
    private ConceptMapper conceptMapper;

    final LocalDate dateStart = LocalDate.of(2024, 4, 8);
    final LocalDate dateEnd = LocalDate.of(2024, 4, 28);

    @Test
    @DisplayName("budgetService should be inject")
    void smokeTest(){
        assertNotNull(budgetService);
    }

    @Test
    @DisplayName("budgetService should return budget from repository")
    void findAllTest(){
        List<Budget> data = new LinkedList<>();
        Budget fakeBudget = new Budget();
        fakeBudget.setNameBudget("Test Service find ALL");
        fakeBudget.setTotal(100.00);
        fakeBudget.setId(100L);

        data.add(fakeBudget);

        when(budgetRepository.findAll()).thenReturn(data);
        List<BudgetDTO> result = budgetService.findAll();

        assertNotNull(result);
        assertEquals(fakeBudget.getId(),result.get(0).getId());
        assertEquals(fakeBudget.getNameBudget(),result.get(0).getNameBudget());
    }
    @Test
    @DisplayName("BudgetService should find Budget by ID")
    void findByIdTest() throws BudgetNotFoundException {
        Budget fakeBudget = new Budget();
        fakeBudget.setNameBudget("Pruebas bsucar un budget");
        fakeBudget.setId(14785L);
        fakeBudget.setStatus(Estatus.PENDIENTE);

        when(budgetRepository.findById(anyLong())).thenReturn(Optional.of(fakeBudget));

        Optional<BudgetDTO> result = budgetService.findById(fakeBudget.getId());

        assertNotNull(result);
        assertEquals(fakeBudget.getNameBudget(),result.get().getNameBudget());
        assertEquals(fakeBudget.getTotal(),result.get().getTotal());
        assertEquals(fakeBudget.getId(),result.get().getId());

    }
    @Test
    @DisplayName("BudgetService should find Budget by ID")
    void NotFindByIdTest() throws BudgetNotFoundException {
        when(budgetRepository.findById(1965L)).thenReturn(Optional.empty());

        assertThrows(BudgetNotFoundException.class, () -> budgetService.findById(1965L));

    }
    @Test
    @DisplayName("budgetService should save a budget in repository")
    void saveTest(){
        CreateBudgetDTO fakeBudget = new CreateBudgetDTO();
        fakeBudget.setNameBudget("Test Service saved");
        fakeBudget.setTotal(100.00);
        fakeBudget.setStatus(Estatus.PENDIENTE);
        fakeBudget.setStartDate(dateStart);
        fakeBudget.setEndDate(dateEnd);

        Budget fakeData = new Budget();
        fakeData.setId(1000L);
        fakeData.setNameBudget("Test Service saved");
        fakeData.setTotal(100.00);
        fakeData.setStatus(Estatus.PENDIENTE);
        fakeData.setStartDate(dateStart);
        fakeData.setEndDate(dateEnd);

        when(budgetRepository.save(any(Budget.class))).thenReturn(fakeData);

        BudgetDTO result = budgetService.save(fakeBudget);

        assertNotNull(result);
        assertEquals(fakeData.getNameBudget(),result.getNameBudget());
        assertEquals(fakeData.getTotal(),result.getTotal());
        assertEquals(fakeData.getStatus(),result.getStatus());
        assertEquals(fakeData.getStartDate(),result.getStartDate());

    }

    @Test
    @DisplayName("budgetService should throws an error if Budget was not found")
    void updateWithErrorTest(){
        Long id = 1900L;
        UpdateBudgetDTO dto = new UpdateBudgetDTO();
        Optional<Budget> dummy = Optional.empty();

        when(budgetRepository.findById(anyLong())).thenReturn(dummy);

        assertThrows(BudgetNotFoundException.class,()->budgetService.update(id,dto));

    }
    @Test
    @DisplayName("budgetService should update a Budget in repository")
    void updateTest() throws BudgetNotFoundException {
        UpdateBudgetDTO fakeBudget = new UpdateBudgetDTO();
        fakeBudget.setNameBudget("Test Update");
        fakeBudget.setTotal(100.00);

        Budget budget = new Budget();
        budget.setId(15987L);
        budget.setNameBudget("Test Update service");
        budget.setTotal(1500.23);

        when(budgetRepository.findById(anyLong())).thenReturn(Optional.of(budget));
        budgetService.update(15987L,fakeBudget);

        assertEquals(fakeBudget.getNameBudget(),budget.getNameBudget());
        assertEquals(fakeBudget.getTotal(),budget.getTotal());
        verify(budgetRepository,times(1)).save(budget);
    }

    @Test
    @DisplayName("budgetService should error to delete a Budget by ID in repository Not found")
    void errorDeleteByIdTest() throws BudgetNotFoundException {
        Long id = 7485963L;

        assertThrows(BudgetNotFoundException.class,()->budgetService.deleteById(id));

    }

    @Test
    @DisplayName("budgetService should delete an existing Budget in repository")
    void deleteByIdTest(){
        // arrange
        Long id = 7485960L;
        Budget fakeBudget = new Budget();
        fakeBudget.setId(id);
        fakeBudget.setNameBudget("Pruebas de eliminado");
        fakeBudget.setTotal(1478.23);
        fakeBudget.setStatus(Estatus.CANCELADO);
        // act
        when(budgetRepository.findById(anyLong())).thenReturn(Optional.of(fakeBudget));

        // asserts
        assertDoesNotThrow(()->budgetService.deleteById(id));

        verify(budgetRepository,times(1)).deleteById(fakeBudget.getId());
    }

    @Test
    @DisplayName("budgetService should calculator Total of the Budget with the Concepts")
    void calculatorTotalBudget(){
        // arrange
        Long id = 7485960L;
        double total = 0;

        Budget fakeBudget = new Budget();
        fakeBudget.setId(id);

        List<Concept> fakeList = new LinkedList<>();
        Concept fakeConcept = new Concept();
        fakeConcept.setBudgetId(fakeBudget);
        fakeConcept.setSubtotal(5000);
        fakeList.add(fakeConcept);

        Concept fakeConcept2 = new Concept();
        fakeConcept2.setBudgetId(fakeBudget);
        fakeConcept2.setSubtotal(5000);
        fakeList.add(fakeConcept2);

        Concept fakeConcept3 = new Concept();
        fakeConcept3.setBudgetId(fakeBudget);
        fakeConcept3.setSubtotal(10000);
        fakeList.add(fakeConcept3);

        // act
        when(budgetRepository.findById(anyLong())).thenReturn(Optional.of(fakeBudget));
        when(conceptRepository.findsConceptsByBudgetId(anyLong())).thenReturn(fakeList);

        List<ConceptDTO> listConcepts = conceptMapper.toDTO(conceptRepository.findsConceptsByBudgetId(id));
        for (ConceptDTO l1:listConcepts   ) {
            total+= l1.getSubtotal();
        }
        assertNotNull(listConcepts);
        assertTrue(total>=0);
        assertEquals(20000,total);

    }
}