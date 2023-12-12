package bedu.org.BudgetCalculator.controller;

import bedu.org.BudgetCalculator.dto.Budget.CreateBudgetDTO;
import bedu.org.BudgetCalculator.dto.Budget.BudgetDTO;
import bedu.org.BudgetCalculator.dto.Budget.UpdateBudgetDTO;
import bedu.org.BudgetCalculator.exception.budget.BudgetNotFoundException;
import bedu.org.BudgetCalculator.service.BudgetService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("budgets")
public class BudgetController {
    @Autowired
    private BudgetService budgetService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BudgetDTO> findAll(){
        return budgetService.findAll() ;
    }
    @GetMapping("{id}")
    public Optional<BudgetDTO> findById(@PathVariable Long id) throws BudgetNotFoundException {
        return budgetService.findById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BudgetDTO save(@Valid @RequestBody CreateBudgetDTO data){
        log.info("Creando presupuesto");
        log.info(data.toString());
        return budgetService.save(data);
    }
    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public BudgetDTO update(@PathVariable Long id, @Valid @RequestBody UpdateBudgetDTO data) throws BudgetNotFoundException {
        log.info("Actualziando presupuesto");
        log.info(data.toString());

        return budgetService.update(id, data) ;
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws BudgetNotFoundException {
        log.info("Borrando presupuesto");
        budgetService.deleteById(id);
    }


}
