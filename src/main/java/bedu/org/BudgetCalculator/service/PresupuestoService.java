package bedu.org.BudgetCalculator.service;

import bedu.org.BudgetCalculator.dto.Presupuesto.CreatePresupuestoDTO;
import bedu.org.BudgetCalculator.dto.Presupuesto.PresupuestoDTO;
import bedu.org.BudgetCalculator.model.Presupuesto;
import bedu.org.BudgetCalculator.repository.PresupuestoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Service
public class PresupuestoService {
    @Autowired
    private PresupuestoRepository presupuestoRepository;

    public List<PresupuestoDTO> getAll(){
        List<Presupuesto> presupuestos = presupuestoRepository.getAll();
        List<PresupuestoDTO> data = new LinkedList<>();
        for (Presupuesto presupuesto: presupuestos      ) {
            data.add(toDTO(presupuesto));
        }

        return data;
    }
    private Presupuesto toModel(PresupuestoDTO dto){
        return new Presupuesto(dto.getId(),dto.getNombre(),dto.getConcepto(),
                dto.getTotal(), dto.getFecha_creacion(),
                dto.getFecha_inicio(),dto.getFecha_fin(),dto.getEstado(),
                dto.isActivo(),dto.isGenerado(),dto.isAceptado());

    }
    private PresupuestoDTO toDTO(Presupuesto model){
        return new PresupuestoDTO(model.getId(),model.getNombre(),
                model.getConcepto(), model.getTotal(), model.getFecha_creacion(),
                model.getFecha_inicio(),model.getFecha_fin(),model.getEstado(),
                model.isActivo(),model.isGenerado(),model.isAceptado());
    }

    public PresupuestoDTO save(CreatePresupuestoDTO data){
        Presupuesto model = toModel(data);
        return toDTO(presupuestoRepository.save(model));
    }
    private Presupuesto toModel(CreatePresupuestoDTO dto){
        final LocalDate fecha = LocalDate.parse("01-01-2000");
        return  new Presupuesto(0,dto.getNombre(),dto.getConcepto(),
                dto.getTotal(), dto.getFecha_creacion(),
                dto.getFecha_inicio(),fecha,dto.getEstado(),
                true,false,false);

    }

}
