package bedu.org.budget_calculator.exception;

public abstract class ResourceNotFoundException extends BaseException {
    protected ResourceNotFoundException(String resourceName, Object id) {
        super("ERR_DATA_NOT_FOUND", resourceName + " not found with ID: " + id, id);
    }
}
