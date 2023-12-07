package bedu.org.BudgetCalculator.service;

import bedu.org.BudgetCalculator.dto.Presupuesto.CreatePresupuestoDTO;
import bedu.org.BudgetCalculator.dto.Presupuesto.PresupuestoDTO;
import bedu.org.BudgetCalculator.mappers.PresupuestoMapper;
import bedu.org.BudgetCalculator.model.Presupuesto;
import bedu.org.BudgetCalculator.repository.PresupuestoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PresupuestoService {
    @Autowired
    private PresupuestoRepository presupuestoRepository;

    @Autowired
    private PresupuestoMapper presupuestoMapper;

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
        existePresupuesto.setNombre(data.getNombre());
        existePresupuesto.setClienteid(data.getClienteid());
        existePresupuesto.setTotal(data.getTotal());
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
