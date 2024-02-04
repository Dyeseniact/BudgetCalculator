package bedu.org.budget_calculator.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import bedu.org.budget_calculator.dto.budget.BudgetDTO;
import bedu.org.budget_calculator.dto.budget.CreateBudgetDTO;
import bedu.org.budget_calculator.dto.budget.UpdateBudgetDTO;
import bedu.org.budget_calculator.exception.budget.BudgetNotFoundException;
import bedu.org.budget_calculator.service.BudgetService;

import java.util.List;
import java.util.Optional;

@Tag(name = "Endpoint of Budget",  description = "CRUD of Budget")
@RestController
@Slf4j
@RequestMapping("budgets")
public class BudgetController {

    private BudgetService budgetService;
    
    @Autowired
    public BudgetController(BudgetService budgetService){
        this.budgetService=budgetService;
    }

    @Operation(summary = "get the budget")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BudgetDTO> findAll(){
        return budgetService.findAll() ;
    }

    @Operation(summary = "get the budget by the ID")
    @GetMapping("{id}")
    public Optional<BudgetDTO> findById(@PathVariable Long id) throws BudgetNotFoundException {
        return budgetService.findById(id);
    }

    @Operation(summary = "creating the budget")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BudgetDTO save(@Valid @RequestBody CreateBudgetDTO data){
        log.info("Creando presupuesto");
        log.info(data.toString());
        return budgetService.save(data);
    }

    @Operation(summary = "updating the budget")
    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public BudgetDTO update(@PathVariable Long id, @Valid @RequestBody UpdateBudgetDTO data) throws BudgetNotFoundException {
        log.info("Actualziando presupuesto");
        log.info(data.toString());

        return budgetService.update(id, data) ;
    }

    @Operation(summary = "deleting the budget")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws BudgetNotFoundException {
        log.info("Borrando presupuesto");
        budgetService.deleteById(id);
    }


}
