package bedu.org.BudgetCalculator.controller;

import bedu.org.BudgetCalculator.dto.Concepto.ConceptoDTO;
import bedu.org.BudgetCalculator.dto.Concepto.CreateConceptoDTO;
import bedu.org.BudgetCalculator.dto.Concepto.UpdateConceptoDTO;
import bedu.org.BudgetCalculator.service.ConceptoService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("conceptos")
public class ConceptoController {
    @Autowired
    private ConceptoService conceptoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ConceptoDTO> findAll(){
        return conceptoService.findAll();
    }
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<ConceptoDTO> findById(@PathVariable Long id){
        return conceptoService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConceptoDTO save(@Valid @RequestBody CreateConceptoDTO data){
        log.info("Creando concepto");
        log.info(data.toString());
        return conceptoService.save(data);
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ConceptoDTO save(@PathVariable Long id,@Valid @RequestBody UpdateConceptoDTO data){
        data.setId(id);
        log.info("Actualizando concepto");
        log.info(data.toString());
        return conceptoService.save(data);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id){
        conceptoService.deleteById(id);
    }
}
