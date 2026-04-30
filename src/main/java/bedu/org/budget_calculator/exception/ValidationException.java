package bedu.org.budget_calculator.exception;

public class ValidationException  extends RuntimeException {
    public ValidationException(String message) {
        super("ERR_VALIDATION", "Error validating data", message);
    }
}
