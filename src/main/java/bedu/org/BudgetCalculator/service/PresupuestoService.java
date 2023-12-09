package bedu.org.BudgetCalculator.service;

import bedu.org.BudgetCalculator.dto.Concept.ConceptDTO;
import bedu.org.BudgetCalculator.dto.Presupuesto.CreatePresupuestoDTO;
import bedu.org.BudgetCalculator.dto.Presupuesto.PresupuestoDTO;
import bedu.org.BudgetCalculator.mapper.ConceptMapper;
import bedu.org.BudgetCalculator.mapper.PresupuestoMapper;
import bedu.org.BudgetCalculator.model.Presupuesto;
import bedu.org.BudgetCalculator.repository.ConceptRepository;
import bedu.org.BudgetCalculator.repository.PresupuestoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PresupuestoService {
    @Autowired
    private PresupuestoRepository presupuestoRepository;

    @Autowired
    private PresupuestoMapper presupuestoMapper;

    @Autowired
    private ConceptRepository conceptoRepository;
    @Autowired
    private ConceptMapper conceptoMapper;

    public List<PresupuestoDTO> findAll(){

        return presupuestoRepository
                .findAll()
                .stream()
                .map(presupuestoMapper::toDTO)
                .toList();
    }

    public Optional<PresupuestoDTO> findById(Long id){
        return presupuestoRepository
                .findById(id)
                .stream()
                .map(presupuestoMapper::toDTO)
                .findFirst();
    }

    public PresupuestoDTO save(CreatePresupuestoDTO data){
        LocalDateTime fecha=LocalDateTime.now();
        data.setFecha_creacion(fecha);
        Presupuesto entity = presupuestoRepository
                .save(presupuestoMapper.toModel(data));

        return presupuestoMapper.toDTO(entity);
    }
    public void deleteById(Long id){

        Presupuesto existePresupuesto = presupuestoRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Presupuesto no encontrado con ID: " + id)) ;
        presupuestoRepository.delete(existePresupuesto);
    }

    public PresupuestoDTO save(Long id, CreatePresupuestoDTO data){
        Presupuesto existePresupuesto = presupuestoRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Presupuesto no encontrado con ID: " + id)) ;

        List<ConceptDTO> listado = conceptoMapper.toDTO(conceptoRepository.findsConceptsByPresupuesto(id));
        double total=0;
        for (int i =0 ; i<listado.size();i++){
           total=total +listado.get(i).getSubtotal();
        }
        existePresupuesto.setNombre(data.getNombre());
        existePresupuesto.setClienteid(data.getClienteid());
        existePresupuesto.setTotal(total);
        existePresupuesto.setFecha_creacion(data.getFecha_creacion());
        existePresupuesto.setFecha_inicio(data.getFecha_inicio());
        existePresupuesto.setFecha_fin(data.getFecha_fin());
        existePresupuesto.setEstado(data.getEstado());
        existePresupuesto.setActivo(data.isActivo());
        existePresupuesto.setGenerado(data.isGenerado());
        existePresupuesto.setAceptado(data.isAceptado());

        Presupuesto updatedPresupuesto = presupuestoRepository.save(existePresupuesto);
        return presupuestoMapper.toDTO(updatedPresupuesto);
    }

}
