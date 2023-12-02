package bedu.org.BudgetCalculator.service;

import bedu.org.BudgetCalculator.dto.Concepto.ConceptoDTO;
import bedu.org.BudgetCalculator.dto.Concepto.CreateConceptoDTO;
import bedu.org.BudgetCalculator.dto.Concepto.UpdateConceptoDTO;
import bedu.org.BudgetCalculator.mappers.Concepto.ConceptoMapper;
import bedu.org.BudgetCalculator.model.Concepto;
import bedu.org.BudgetCalculator.repository.ConceptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConceptoService {
    @Autowired
    private ConceptoMapper conceptoMapper;
    @Autowired
    private ConceptoRepository conceptoRepository;

    public List<ConceptoDTO> findAll(){
        return conceptoRepository
                .findAll()
                .stream()
                .map(conceptoMapper::toDTO)
                .toList()
        ;
    }
    public Optional<ConceptoDTO> findById(Long id){
        return conceptoRepository
                .findById(id)
                .stream()
                .map(conceptoMapper::toDTO)
                .findFirst()
                ;
    }

    public ConceptoDTO save(CreateConceptoDTO data){
        Concepto entity = conceptoRepository
                .save(conceptoMapper.toModel(data));
        return conceptoMapper.toDTO(entity)       ;
    }
    public ConceptoDTO save(UpdateConceptoDTO data){

        Concepto entity = conceptoRepository
                            .save(conceptoMapper.toModel(data));
        return conceptoMapper.toDTO(entity);
    }

    public void deleteById(Long id){
        conceptoRepository.deleteById(id);
    }
}
