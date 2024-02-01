package bedu.org.BudgetCalculator.exception.material;

import bedu.org.BudgetCalculator.exception.RuntimeException;
public class MaterialNotFoundException extends RuntimeException {
    public MaterialNotFoundException(long materialId) {
        super("ERR_DATA_NOT_FOUND","Material not found with ID:",materialId);
    }
}
