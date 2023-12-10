package bedu.org.BudgetCalculator.service;

import bedu.org.BudgetCalculator.dto.Budget.BudgetDTO;
import bedu.org.BudgetCalculator.dto.Budget.CreateBudgetDTO;
import bedu.org.BudgetCalculator.dto.Budget.UpdateBudgetDTO;
import bedu.org.BudgetCalculator.dto.Concept.ConceptDTO;
import bedu.org.BudgetCalculator.exception.budget.BudgetNotFoundException;
import bedu.org.BudgetCalculator.mapper.BudgetMapper;
import bedu.org.BudgetCalculator.mapper.ConceptMapper;
import bedu.org.BudgetCalculator.model.Budget;
import bedu.org.BudgetCalculator.repository.ConceptRepository;
import bedu.org.BudgetCalculator.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BudgetService {
    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private BudgetMapper budgetMapper;

    @Autowired
    private ConceptRepository conceptoRepository;
    @Autowired
    private ConceptMapper conceptoMapper;

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

    public BudgetDTO save(CreateBudgetDTO data){
        LocalDateTime fecha=LocalDateTime.now();
        data.setCreationDate(fecha);
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

    public BudgetDTO save(Long id, UpdateBudgetDTO data) throws BudgetNotFoundException {
        Optional<Budget> resultBudget = budgetRepository.findById(id);
        if (!resultBudget.isPresent()){
            throw new BudgetNotFoundException(id);
        }

        Budget budget = resultBudget.get();

        List<ConceptDTO> listado = conceptoMapper.toDTO(conceptoRepository.findsConceptsByBudgetId(id));
        double total=0;
        for (int i =0 ; i<listado.size();i++){
           total=total +listado.get(i).getSubtotal();
        }
        data.setTotal(total);
        budgetMapper.update(budget,data);
        budgetRepository.save(budget);

        return budgetMapper.toDTO(budget);
    }

}
