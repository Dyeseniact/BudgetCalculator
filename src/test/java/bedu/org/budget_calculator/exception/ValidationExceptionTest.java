package bedu.org.budget_calculator.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ValidationExceptionTest {
    @Test
    @DisplayName("Should create ValidationException with message")
    void createValidationException() {
        // Mock message
        String message = "Invalid input data";

        // Create ValidationException
        ValidationException exception = new ValidationException(message);

        // Verify the exception properties
        assertNotNull(exception);
        assertEquals("ERR_VALIDATION", exception.getCode());
        assertEquals("Error validating data", exception.getMessage());
        assertEquals(message, exception.getDetails());

        // Verify the call to super constructor
        assertEquals("ERR_VALIDATION", exception.getCode());
        assertEquals("Error validating data", exception.getMessage());
    }
}
