package bedu.org.budget_calculator.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bedu.org.budget_calculator.dto.concept.ConceptDTO;
import bedu.org.budget_calculator.dto.concept.CreateConceptDTO;
import bedu.org.budget_calculator.dto.concept.UpdateConceptDTO;
import bedu.org.budget_calculator.exception.concept.ConceptNotFoundException;
import bedu.org.budget_calculator.mapper.ConceptMapper;
import bedu.org.budget_calculator.model.Concept;
import bedu.org.budget_calculator.repository.ConceptRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ConceptService {

    private ConceptMapper conceptMapper;
    private ConceptRepository conceptRepository;


    //  Error creating bean with name 'activityController' - Al tener un constructor por inyección.
    //  Se corrige unificado todos en un mismo constructor.
    @Autowired
    public ConceptService(ConceptMapper conceptMapper, ConceptRepository conceptRepository) {
        this.conceptMapper = conceptMapper;
        this.conceptRepository = conceptRepository;
    }

    public List<ConceptDTO> findAll(){
        return conceptRepository
                .findAll()
                .stream()
                .map(conceptMapper::toDTO)
                .toList()
        ;
    }
    public Optional<ConceptDTO> findById(Long id) throws ConceptNotFoundException {
        Optional<Concept> resultConcept = conceptRepository.findById(id);
        if (!resultConcept.isPresent()){
            throw new ConceptNotFoundException(id);
        }
        return resultConcept
                .stream()
                .map(conceptMapper::toDTO)
                .findFirst()
                ;
    }

    @Transactional
    public ConceptDTO save(CreateConceptDTO data){
        data.setSubtotal(data.getQuantity()*data.getUnitPrice());
        Concept entity = conceptRepository
                .save(conceptMapper.toModel(data));
        return conceptMapper.toDTO(entity)       ;
    }
    @Transactional
    public void update(Long id, UpdateConceptDTO data) throws ConceptNotFoundException {
        Optional<Concept> resultConcept = conceptRepository.findById(id);
        if (!resultConcept.isPresent()){
            throw new ConceptNotFoundException(id);
        }
        Concept concept = resultConcept.get();
        data.setSubtotal(data.getQuantity()*data.getUnitPrice());

        // Actualizar el Concepto en la base de datos
        conceptMapper.update(concept,data);
        conceptRepository.save(concept);
    }

    public void deleteById(Long id) throws ConceptNotFoundException {
        Optional<Concept> resultConcept = conceptRepository.findById(id);
        if (!resultConcept.isPresent()){
            throw new ConceptNotFoundException(id);
        }
        conceptRepository.deleteById(id);
    }

    public List<ConceptDTO> findConceptsByBudget(Long id){
        List<Concept> listado = conceptRepository.findsConceptsByBudgetId(id);
        return  conceptMapper.toDTO(listado);
    }
}
