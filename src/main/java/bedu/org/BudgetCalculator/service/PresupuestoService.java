package bedu.org.BudgetCalculator.service;

import bedu.org.BudgetCalculator.dto.Presupuesto.CreatePresupuestoDTO;
import bedu.org.BudgetCalculator.dto.Presupuesto.PresupuestoDTO;
import bedu.org.BudgetCalculator.mappers.Presupuesto.PresupuestoMapper;
import bedu.org.BudgetCalculator.model.Presupuesto;
import bedu.org.BudgetCalculator.repository.PresupuestoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
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


}
