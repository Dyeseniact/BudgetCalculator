package bedu.org.budget_calculator.exception;

public class BadRequestException extends RuntimeException{

    public BadRequestException(String details) {
        super("ERR_BAD_REQUEST", "The request body is incorrect.", details);
    }
}
