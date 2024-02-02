package bedu.org.budget_calculator.exception.budget;

import bedu.org.budget_calculator.exception.RuntimeException;

public class BudgetNotFoundException extends RuntimeException {
    public BudgetNotFoundException(Long id) {
        super("ERR_BUDGET_NOT_FOUND", "No se encontró el presupuesto.", id);
    }
}
