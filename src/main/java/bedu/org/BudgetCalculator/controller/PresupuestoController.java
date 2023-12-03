package bedu.org.BudgetCalculator.controller;

import bedu.org.BudgetCalculator.dto.Presupuesto.CreatePresupuestoDTO;
import bedu.org.BudgetCalculator.dto.Presupuesto.PresupuestoDTO;
import bedu.org.BudgetCalculator.dto.Presupuesto.UpdatePresupuestoDTO;
import bedu.org.BudgetCalculator.service.PresupuestoService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("presupuestos")
public class PresupuestoController {

    @Autowired
    private PresupuestoService presupuestoService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PresupuestoDTO> findAll(){
        return presupuestoService.findAll() ;
    }
    @GetMapping("{id}")
    public Optional<PresupuestoDTO> findById(@PathVariable Long id){
        return presupuestoService.findById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PresupuestoDTO save(@Valid @RequestBody CreatePresupuestoDTO data){
        log.info("Creando presupuesto");
        log.info(data.toString());
        return presupuestoService.save(data);
    }
    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public PresupuestoDTO update(@PathVariable Long id, @Valid @RequestBody CreatePresupuestoDTO data){
        log.info("Actualziando presupuesto");
        log.info(data.toString());
        //data.setId(id);
        return presupuestoService.save(id, data) ;
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        presupuestoService.deleteById(id);
    }


}
