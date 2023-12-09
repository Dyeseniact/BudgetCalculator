package bedu.org.BudgetCalculator.exception;

public class ValidationException  extends RuntimeException {
    public ValidationException(String message) {
        super("ERR_VALIDATION", "Error validating data", message);
    }
}
