package bedu.org.budget_calculator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDTO {

    private String code;
    private String message;
    private Object details;
}
