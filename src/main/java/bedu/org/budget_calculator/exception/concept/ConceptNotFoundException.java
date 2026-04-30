package bedu.org.budget_calculator.exception.concept;

import bedu.org.budget_calculator.exception.RuntimeException;

public class ConceptNotFoundException extends RuntimeException {
    public ConceptNotFoundException(Long id){
        super("ERR_DATA_NOT_FOUND","No se encontró el concepto especificado",id);
    }
}
