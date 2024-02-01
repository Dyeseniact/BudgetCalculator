package bedu.org.budget_calculator.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import bedu.org.budget_calculator.dto.ErrorDTO;
import bedu.org.budget_calculator.exception.RuntimeException;
import bedu.org.budget_calculator.exception.material.MaterialNotFoundException;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO validationError(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<String> errors = fieldErrors.stream().map(x -> x.getDefaultMessage()).toList();
        return new ErrorDTO("ERR_VALID", "A error ocurred procesando input data", errors);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO applicationError(RuntimeException ex) {
        return new ErrorDTO(ex.getCode(), ex.getMessage(), ex.getDetails());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO unknownError(Exception ex) {
        log.error(ex.getMessage());
        return new ErrorDTO("ERR_UNKNOWN", "An unknown error ocurred", null);
    }

    @ExceptionHandler(MaterialNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO materialNotFound(MaterialNotFoundException ex) {
        return new ErrorDTO(ex.getCode(), ex.getMessage(), ex.getDetails());
    }
}
