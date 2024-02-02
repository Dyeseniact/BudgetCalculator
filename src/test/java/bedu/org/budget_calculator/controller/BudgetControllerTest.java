package bedu.org.budget_calculator.controller;

import bedu.org.budget_calculator.dto.budget.BudgetDTO;
import bedu.org.budget_calculator.dto.budget.CreateBudgetDTO;
import bedu.org.budget_calculator.dto.budget.UpdateBudgetDTO;
import bedu.org.budget_calculator.exception.budget.BudgetNotFoundException;
import bedu.org.budget_calculator.model.Estatus;
import bedu.org.budget_calculator.service.BudgetService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class BudgetControllerTest {
    @MockBean
    private BudgetService budgetService;
    @Autowired
    private BudgetController budgetController;

    final LocalDate dateStart = LocalDate.of(2024, 4, 8);
    final LocalDate dateEnd = LocalDate.of(2024, 4, 28);

    @Test
    @DisplayName("The BudgetController should be injected by Spring")
    void smokeTest(){
        assertNotNull(budgetController, "Controller was injected");
    }

    @Test
    @DisplayName("The BudgetController should return a list of Budgets")
    void findAllTest()  {
        //Arrange
        List<BudgetDTO> fakeData = new LinkedList<>();
        BudgetDTO fakeBudget = new BudgetDTO();
        fakeBudget.setId(100005);
        fakeBudget.setNameBudget("DEMO TEST: Find budget fake");
        fakeBudget.setTotal(10.00);
        fakeBudget.setStatus(Estatus.PENDIENTE);
        fakeBudget.setStartDate(dateStart);
        fakeBudget.setEndDate(dateEnd);

        fakeData.add(fakeBudget);

        //Mock to method findAll()
        when(budgetService.findAll()).thenReturn(fakeData);

        //Act
        List<BudgetDTO> result = budgetService.findAll();

        //Asserts
        assertNotNull(result);
        assertTrue(result.size()>0);
        assertEquals(fakeData,result);
        assertEquals(fakeBudget.getId(), result.get(0).getId());
        assertEquals(fakeBudget.getNameBudget(), result.get(0).getNameBudget());
        assertEquals(fakeBudget.getStatus(), result.get(0).getStatus());
        assertEquals(fakeBudget.getStartDate(), result.get(0).getStartDate());
        assertEquals(fakeBudget.getTotal(), result.get(0).getTotal());

    }

    @Test
    @DisplayName("The BudgetController should return one budget")
    void findByIdTest() throws BudgetNotFoundException {
        //Arrange
        BudgetDTO fakeBudget = new BudgetDTO();
        fakeBudget.setId(100005);
        fakeBudget.setNameBudget("DEMO TEST: Find budget fake");
        fakeBudget.setTotal(10.00);
        fakeBudget.setStatus(Estatus.PENDIENTE);
        fakeBudget.setStartDate(dateStart);
        fakeBudget.setEndDate(dateEnd);


        //Mock to method findAll()
        when(budgetService.findById(anyLong())).thenReturn(Optional.of(fakeBudget));

        //ACT
        Optional<BudgetDTO> result = budgetController.findById(fakeBudget.getId());

        //Asserts
        assertNotNull(result);
        assertEquals(fakeBudget.getId(),result.get().getId());
        assertEquals(fakeBudget.getNameBudget(),result.get().getNameBudget());
        assertEquals(fakeBudget.getTotal(),result.get().getTotal());
        assertEquals(fakeBudget.getStatus(),result.get().getStatus());

    }

    @Test
    @DisplayName("The BudgetController should save a budget")
    void saveTest(){
        //Arrange
        CreateBudgetDTO fakeBudget = new CreateBudgetDTO();

        fakeBudget.setNameBudget("DEMO TEST: Find budget fake");
        fakeBudget.setTotal(10.00);
        fakeBudget.setStatus(Estatus.PENDIENTE);
        fakeBudget.setStartDate(dateStart);
        fakeBudget.setEndDate(dateEnd);
        fakeBudget.setStatus(Estatus.PENDIENTE);


        BudgetDTO saved = new BudgetDTO();
        saved.setId(55);
        saved.setNameBudget("DEMO TEST: Find budget fake");
        saved.setTotal(10.00);
        saved.setStatus(Estatus.PENDIENTE);
        saved.setStartDate(dateStart);
        saved.setEndDate(dateEnd);

        //mock
        when(budgetService.save(any(CreateBudgetDTO.class))).thenReturn(saved);

        //Act
        BudgetDTO result = budgetService.save(fakeBudget);

        //asserts
        assertNotNull(result);
        assertEquals(fakeBudget.getStatus(),result.getStatus());
        assertEquals(fakeBudget.getNameBudget(),result.getNameBudget());
        assertEquals(fakeBudget.getTotal(),result.getTotal());
        assertEquals(fakeBudget.getStartDate(),result.getStartDate());

    }


    @Test
    @DisplayName("The BudgetController should update a budget")
    void updateTest() throws BudgetNotFoundException {
        //Arrange
        UpdateBudgetDTO fakeBudget = new UpdateBudgetDTO();
        fakeBudget.setTotal(100.26);
        fakeBudget.setNameBudget("Test Update");
        fakeBudget.setStatus(Estatus.ACTIVO);

        budgetController.update(400L,fakeBudget);

        //Verify
        verify(budgetService,times(1)).update(400L,fakeBudget);

    }
    @Test
    @DisplayName("The BudgetController should delete a budget")
    void deleteTest() throws BudgetNotFoundException {
        //Arrange
        budgetController.delete(4000L);

        //Verify
        verify(budgetService,times(1)).deleteById(4000L);

    }

}