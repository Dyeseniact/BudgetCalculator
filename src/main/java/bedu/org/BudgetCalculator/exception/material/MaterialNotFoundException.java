package bedu.org.BudgetCalculator.exception.material;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MaterialNotFoundException extends RuntimeException {

    public MaterialNotFoundException(long materialId) {
        super("Material not found with ID: " + materialId);
    }
}
