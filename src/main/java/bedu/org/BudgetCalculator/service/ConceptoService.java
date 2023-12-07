package bedu.org.BudgetCalculator.service;

import bedu.org.BudgetCalculator.dto.Concepto.ConceptoDTO;
import bedu.org.BudgetCalculator.dto.Concepto.CreateConceptoDTO;
import bedu.org.BudgetCalculator.mappers.ConceptoMapper;
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
    public ConceptoDTO save(Long id,CreateConceptoDTO data){

        Concepto existeConcepto = conceptoRepository.findById(id).orElseThrow(() -> new RuntimeException("Concepto no encontrado con ID: " + id));
        existeConcepto.setDescription(data.getDescription());
        existeConcepto.setCantidad(data.getCantidad());
        existeConcepto.setPrecioUnit(data.getPrecioUnit());
        existeConcepto.setSubtotal(data.getSubtotal());
        existeConcepto.setFecha_inicio(data.getFecha_inicio());
        existeConcepto.setFecha_fin(data.getFecha_fin());
        existeConcepto.setPresupuestoId(data.getPresupuestoId());

        // Actualizar el Concepto en la base de datos
        Concepto updatedConcepto = conceptoRepository.save(existeConcepto);

        return conceptoMapper.toDTO(updatedConcepto);
    }

    public void deleteById(Long id){
        Concepto existeConcepto = conceptoRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Concepto no encontrado con ID: "+id));

        conceptoRepository.deleteById(existeConcepto.getId());
    }
}
