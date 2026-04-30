package bedu.org.budget_calculator.exception;

import lombok.Getter;

@Getter
public abstract class BaseException extends java.lang.RuntimeException {

    private final String code;
    private final transient Object details;

    protected BaseException(String code, String message, Object details) {
        super(message);
        this.code = code;
        this.details = details;
    }
}
