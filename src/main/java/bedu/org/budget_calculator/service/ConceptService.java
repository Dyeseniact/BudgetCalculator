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

    private ConceptMapper conceptoMapper;

    @Autowired
    public ConceptService(ConceptMapper conceptoMapper) {
        this.conceptoMapper = conceptoMapper;
    }

    private ConceptRepository conceptoRepository;

    @Autowired
    public ConceptService(ConceptRepository conceptoRepository) {
        this.conceptoRepository = conceptoRepository;
    }

    public List<ConceptDTO> findAll(){
        return conceptoRepository
                .findAll()
                .stream()
                .map(conceptoMapper::toDTO)
                .toList()
        ;
    }
    public Optional<ConceptDTO> findById(Long id) throws ConceptNotFoundException {
        Optional<Concept> resultConcept = conceptoRepository.findById(id);
        if (!resultConcept.isPresent()){
            throw new ConceptNotFoundException(id);
        }
        return resultConcept
                .stream()
                .map(conceptoMapper::toDTO)
                .findFirst()
                ;
    }

    @Transactional
    public ConceptDTO save(CreateConceptDTO data){
        data.setSubtotal(data.getQuantity()*data.getUnitPrice());
        Concept entity = conceptoRepository
                .save(conceptoMapper.toModel(data));
        return conceptoMapper.toDTO(entity)       ;
    }
    @Transactional
    public void update(Long id, UpdateConceptDTO data) throws ConceptNotFoundException {
        Optional<Concept> resultConcept = conceptoRepository.findById(id);
        if (!resultConcept.isPresent()){
            throw new ConceptNotFoundException(id);
        }
        Concept concept = resultConcept.get();
        data.setSubtotal(data.getQuantity()*data.getUnitPrice());

        // Actualizar el Concepto en la base de datos
        conceptoMapper.update(concept,data);
        conceptoRepository.save(concept);
    }

    public void deleteById(Long id) throws ConceptNotFoundException {
        Optional<Concept> resultConcept = conceptoRepository.findById(id);
        if (!resultConcept.isPresent()){
            throw new ConceptNotFoundException(id);
        }
        conceptoRepository.deleteById(id);
    }

    public List<ConceptDTO> findConceptsByBudget(Long id){
        List<Concept> listado = conceptoRepository.findsConceptsByBudgetId(id);
        return  conceptoMapper.toDTO(listado);
    }
}
