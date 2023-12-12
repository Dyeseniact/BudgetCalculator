package bedu.org.BudgetCalculator.exception.concept;

import bedu.org.BudgetCalculator.exception.RuntimeException;

public class ConceptNotFoundException extends RuntimeException {
    public ConceptNotFoundException(Long id){
        super("ERR_DATA_NOT_FOUND","No se encontró el concepto especificado",id);
    }
}
