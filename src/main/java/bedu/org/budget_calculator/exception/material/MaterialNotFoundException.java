package bedu.org.budget_calculator.exception.material;

import bedu.org.budget_calculator.exception.RuntimeException;

public class MaterialNotFoundException extends RuntimeException {
    public MaterialNotFoundException(long materialId) {
        super("ERR_DATA_NOT_FOUND","Material not found with ID:",materialId);
    }
}