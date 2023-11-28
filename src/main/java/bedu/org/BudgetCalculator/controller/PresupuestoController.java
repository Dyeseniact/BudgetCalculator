package bedu.org.BudgetCalculator.controller;

import bedu.org.BudgetCalculator.dto.Presupuesto.CreatePresupuestoDTO;
import bedu.org.BudgetCalculator.dto.Presupuesto.PresupuestoDTO;
import bedu.org.BudgetCalculator.service.PresupuestoService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/presupuestos")
public class PresupuestoController {

    @Autowired
    private PresupuestoService presupuestoService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PresupuestoDTO> getAll(){
        return presupuestoService.getAll() ;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PresupuestoDTO save(@Valid @RequestBody CreatePresupuestoDTO data){
        log.info("Creando presupuesto");
        log.info(data.toString());
        return presupuestoService.save(data);
    }

}
