package bedu.org.budget_calculator.advice;

import bedu.org.budget_calculator.dto.ErrorDTO;
import bedu.org.budget_calculator.exception.BaseException;
import bedu.org.budget_calculator.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleValidation(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<ValidationErrorDetail> errors = fieldErrors.stream()
                .map(f -> new ValidationErrorDetail(f.getField(), f.getDefaultMessage()))
                .toList();
        return new ErrorDTO("ERR_VALIDATION", "Invalid input data", errors);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleNotFound(ResourceNotFoundException ex) {
        return new ErrorDTO(ex.getCode(), ex.getMessage(), ex.getDetails());
    }

    @ExceptionHandler(BaseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO handleApplicationError(BaseException ex) {
        return new ErrorDTO(ex.getCode(), ex.getMessage(), ex.getDetails());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO handleUnknownError(Exception ex) {
        log.error("Unhandled exception occurred", ex);
        return new ErrorDTO("ERR_UNKNOWN", "An unexpected error occurred", null);
    }

    /**
     * Inner record to provide structured validation errors
     */
    public record ValidationErrorDetail(String field, String message) {}
}
