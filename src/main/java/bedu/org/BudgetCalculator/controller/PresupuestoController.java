package bedu.org.BudgetCalculator.controller;

import bedu.org.BudgetCalculator.dto.Presupuesto.CreatePresupuestoDTO;
import bedu.org.BudgetCalculator.dto.Presupuesto.PresupuestoDTO;
import bedu.org.BudgetCalculator.service.PresupuestoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PresupuestoController {

    @Autowired
    private PresupuestoService presupuestoService;
    @GetMapping("/ObtenerPresupuestos")
    public List<PresupuestoDTO> getAll(){
        return presupuestoService.getAll() ;
    }
    @PostMapping("/CrearPresupuesto")
    public PresupuestoDTO save(@Valid @RequestBody CreatePresupuestoDTO data){
        return presupuestoService.save(data);
    }

}
