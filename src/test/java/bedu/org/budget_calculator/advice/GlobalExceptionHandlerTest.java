package bedu.org.budget_calculator.advice;

import bedu.org.budget_calculator.dto.ErrorDTO;
import bedu.org.budget_calculator.exception.RuntimeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    @DisplayName("Should handle RuntimeException and return INTERNAL_SERVER_ERROR")
    void handleApplicationError() {
        // Mock a runtime exception
        RuntimeException exception = new RuntimeException("ERR_RUNTIME", "An application error occurred", Collections.singletonList("Additional details"));

        // Call the handler method
        ErrorDTO errorDTO = handler.applicationError(exception);

        // Verify the response
        assertNotNull(errorDTO);
        assertEquals("ERR_RUNTIME", errorDTO.getCode());
        assertEquals("An application error occurred", errorDTO.getMessage());
        assertEquals(List.of("Additional details"), errorDTO.getDetails());
    }

    @Test
    @DisplayName("Should handle unknown Exception and return INTERNAL_SERVER_ERROR")
    void handleUnknownError() {
        // Mock an unknown exception
        Exception exception = new Exception("An unknown error ocurred");

        // Call the handler method
        ErrorDTO errorDTO = handler.unknownError(exception);

        // Verify the response
        assertNotNull(errorDTO);
        assertEquals("ERR_UNKNOWN", errorDTO.getCode());
        assertEquals("An unknown error ocurred", errorDTO.getMessage());
        assertEquals(null, errorDTO.getDetails());
    }

}
