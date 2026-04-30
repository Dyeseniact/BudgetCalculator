package bedu.org.budget_calculator.exception.budget;

import bedu.org.budget_calculator.exception.ResourceNotFoundException;

public class BudgetNotFoundException extends ResourceNotFoundException {
    public BudgetNotFoundException(Long id) {
        super("Budget", id);
    }
}

