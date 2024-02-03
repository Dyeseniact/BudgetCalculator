package bedu.org.budget_calculator.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BadRequestExceptionTest {
    @Test
    @DisplayName("Should create BadRequestException with details")
    void createBadRequestException() {
        // Mock details
        String details = "Invalid request body";

        // Create BadRequestException
        BadRequestException exception = new BadRequestException(details);

        // Verify the exception properties
        assertNotNull(exception);
        assertEquals("ERR_BAD_REQUEST", exception.getCode());
        assertEquals("The request body is incorrect.", exception.getMessage());
        assertEquals("Invalid request body", exception.getDetails());
    }
}
