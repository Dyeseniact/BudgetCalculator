package bedu.org.budget_calculator.exception.concept;

import bedu.org.budget_calculator.exception.ResourceNotFoundException;

public class ConceptNotFoundException extends ResourceNotFoundException {
    public ConceptNotFoundException(Long id){
        super("Concept", id);
    }
}

