package bedu.org.budget_calculator.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bedu.org.budget_calculator.dto.budget.BudgetDTO;
import bedu.org.budget_calculator.dto.budget.CreateBudgetDTO;
import bedu.org.budget_calculator.dto.budget.UpdateBudgetDTO;
import bedu.org.budget_calculator.dto.concept.ConceptDTO;
import bedu.org.budget_calculator.exception.budget.BudgetNotFoundException;
import bedu.org.budget_calculator.mapper.BudgetMapper;
import bedu.org.budget_calculator.mapper.ConceptMapper;
import bedu.org.budget_calculator.model.Budget;
import bedu.org.budget_calculator.repository.BudgetRepository;
import bedu.org.budget_calculator.repository.ConceptRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BudgetService {
    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private BudgetMapper budgetMapper;

    @Autowired
    private ConceptRepository conceptRepository;
    @Autowired
    private ConceptMapper conceptMapper;

    public List<BudgetDTO> findAll(){

        return budgetRepository
                .findAll()
                .stream()
                .map(budgetMapper::toDTO)
                .toList();
    }

    public Optional<BudgetDTO> findById(Long id) throws BudgetNotFoundException {
        Optional<Budget> resultBudget = budgetRepository.findById(id);
        if (!resultBudget.isPresent()){
            throw new BudgetNotFoundException(id);
        }
        return resultBudget
                .stream()
                .map(budgetMapper::toDTO)
                .findFirst();
    }
    @Transactional
    public BudgetDTO save(CreateBudgetDTO data){

        Budget entity = budgetRepository
                .save(budgetMapper.toModel(data));

        return budgetMapper.toDTO(entity);
    }
    public void deleteById(Long id) throws BudgetNotFoundException {
        Optional<Budget> resultBudget = budgetRepository.findById(id);
        if (!resultBudget.isPresent()){
            throw new BudgetNotFoundException(id);
        }
        budgetRepository.deleteById(id);
    }

    @Transactional
    public BudgetDTO update(Long id, UpdateBudgetDTO data) throws BudgetNotFoundException {
        Optional<Budget> resultBudget = budgetRepository.findById(id);
        if (!resultBudget.isPresent()){
            throw new BudgetNotFoundException(id);
        }

        Budget budget = resultBudget.get();

        double total = calculatorTotalBudget(id);
        data.setTotal(total);
        budgetMapper.update(budget,data);
        budgetRepository.save(budget);

        return budgetMapper.toDTO(budget);
    }

    //SE REFACTORIZO, SE SEPARO DEL UPDATE
    public double calculatorTotalBudget(Long id){
        List<ConceptDTO> listConcepts = conceptMapper.toDTO(conceptRepository.findsConceptsByBudgetId(id));
        double total=0;

        for (ConceptDTO l1:listConcepts   ) {
            total+= l1.getSubtotal();
        }
        return total;
    }

}
