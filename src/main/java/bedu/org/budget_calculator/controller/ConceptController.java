package bedu.org.budget_calculator.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import bedu.org.budget_calculator.dto.concept.ConceptDTO;
import bedu.org.budget_calculator.dto.concept.CreateConceptDTO;
import bedu.org.budget_calculator.dto.concept.UpdateConceptDTO;
import bedu.org.budget_calculator.exception.concept.ConceptNotFoundException;
import bedu.org.budget_calculator.service.ConceptService;

import java.util.List;
import java.util.Optional;

@Tag(name = "Endpoint of Concept",  description = "CRUD of Concept")
@RestController
@Slf4j
@RequestMapping("concepts")
public class ConceptController {
    @Autowired
    private ConceptService conceptoService;

    @Operation(summary = "get the concept")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ConceptDTO> findAll(){
        return conceptoService.findAll();
    }

    @Operation(summary = "get the concept by ID")
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<ConceptDTO> findById(@PathVariable Long id) throws ConceptNotFoundException {
        return conceptoService.findById(id);
    }

    @Operation(summary = "creating the concept")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConceptDTO save(@Valid @RequestBody CreateConceptDTO data){
        log.info("Creando concepto");
        log.info(data.toString());
        return conceptoService.save(data);
    }

    @Operation(summary = "updating the concept")
    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id,@Valid @RequestBody UpdateConceptDTO data) throws ConceptNotFoundException {

        log.info("Actualizando concepto");
        log.info(data.toString());
        conceptoService.update(id,data);
    }

    @Operation(summary = "deleting the concept")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws ConceptNotFoundException {
        log.info("Eliminando concepto");
        conceptoService.deleteById(id);
    }

    @GetMapping("/budgets/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<ConceptDTO> findConceptsByBudget(@PathVariable Long id){
        log.info("Listando conceptos por Presupuesto");
        return conceptoService.findConceptsByBudget(id);
    }

}
