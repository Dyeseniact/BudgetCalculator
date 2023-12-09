package bedu.org.BudgetCalculator.controller;

import bedu.org.BudgetCalculator.dto.Concept.ConceptDTO;
import bedu.org.BudgetCalculator.dto.Concept.CreateConceptDTO;
import bedu.org.BudgetCalculator.dto.Concept.UpdateConceptDTO;
import bedu.org.BudgetCalculator.exception.concept.ConceptNotFoundException;
import bedu.org.BudgetCalculator.service.ConceptService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("concepts")
public class ConceptController {
    @Autowired
    private ConceptService conceptoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ConceptDTO> findAll(){
        return conceptoService.findAll();
    }
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<ConceptDTO> findById(@PathVariable Long id) throws ConceptNotFoundException {
        return conceptoService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConceptDTO save(@Valid @RequestBody CreateConceptDTO data){
        log.info("Creando concepto");
        log.info(data.toString());
        return conceptoService.save(data);
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id,@Valid @RequestBody UpdateConceptDTO data) throws ConceptNotFoundException {
        //data.setId(id);
        log.info("Actualizando concepto");
        log.info(data.toString());
        conceptoService.update(id,data);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws ConceptNotFoundException {
        log.info("Eliminando concepto");
        conceptoService.deleteById(id);
    }
}
