package bedu.org.BudgetCalculator.service;

import bedu.org.BudgetCalculator.dto.Concept.ConceptDTO;
import bedu.org.BudgetCalculator.dto.Concept.CreateConceptDTO;
import bedu.org.BudgetCalculator.dto.Concept.UpdateConceptDTO;
import bedu.org.BudgetCalculator.exception.Concept.ConceptNotFoundException;
import bedu.org.BudgetCalculator.mapper.ConceptMapper;
import bedu.org.BudgetCalculator.model.Concept;
import bedu.org.BudgetCalculator.repository.ConceptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConceptService {
    @Autowired
    private ConceptMapper conceptoMapper;
    @Autowired
    private ConceptRepository conceptoRepository;

    public List<ConceptDTO> findAll(){
        return conceptoRepository
                .findAll()
                .stream()
                .map(conceptoMapper::toDTO)
                .toList()
        ;
    }
    public Optional<ConceptDTO> findById(Long id) throws ConceptNotFoundException {
        Optional<Concept> result = conceptoRepository.findById(id);
        if (!result.isPresent()){
            throw new ConceptNotFoundException(id);
        }
        return result
                .stream()
                .map(conceptoMapper::toDTO)
                .findFirst()
                ;
    }

    public ConceptDTO save(CreateConceptDTO data){
        Concept entity = conceptoRepository
                .save(conceptoMapper.toModel(data));
        return conceptoMapper.toDTO(entity)       ;
    }
    public void update(Long id, UpdateConceptDTO data) throws ConceptNotFoundException {

        Optional<Concept> existeConcepto = conceptoRepository.findById(id);
        if (!existeConcepto.isPresent()){
            throw new ConceptNotFoundException(id);
        }
        Concept concepto = existeConcepto.get();

        // Actualizar el Concepto en la base de datos
        conceptoMapper.update(concepto,data);
        conceptoRepository.save(concepto);
    }

    public void deleteById(Long id) throws ConceptNotFoundException {
        Optional<Concept> existeConcepto = conceptoRepository.findById(id);
        if (!existeConcepto.isPresent()){
            throw new ConceptNotFoundException(id);
        }
        conceptoRepository.deleteById(id);
    }
}
