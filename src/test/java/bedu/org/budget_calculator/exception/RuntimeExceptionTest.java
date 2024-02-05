package bedu.org.budget_calculator.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RuntimeExceptionTest {
    @Test
    @DisplayName("Should create RuntimeException with details")
    void createRuntimeException() {
        // Mock details
        Object details = Collections.singletonMap("key", "value");

        // Create RuntimeException
        RuntimeException exception = new RuntimeException("ERR_RUNTIME", "An error occurred", details);

        // Verify the exception properties
        assertNotNull(exception);
        assertEquals("ERR_RUNTIME", exception.getCode());
        assertEquals("An error occurred", exception.getMessage());
        assertEquals(details, exception.getDetails());
    }
}
