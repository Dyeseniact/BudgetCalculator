package bedu.org.budget_calculator.exception.material;

import bedu.org.budget_calculator.exception.ResourceNotFoundException;

public class MaterialNotFoundException extends ResourceNotFoundException {
    public MaterialNotFoundException(long materialId) {
        super("Material", materialId);
    }
}