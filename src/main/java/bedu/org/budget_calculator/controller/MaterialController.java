package bedu.org.budget_calculator.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import bedu.org.budget_calculator.dto.material.CreateMaterialDTO;
import bedu.org.budget_calculator.dto.material.MaterialDTO;
import bedu.org.budget_calculator.dto.material.UpdateMaterialDTO;
import bedu.org.budget_calculator.exception.material.MaterialNotFoundException;
import bedu.org.budget_calculator.service.MaterialService;

import java.util.List;

@Tag(name = "Endpoint of Material",  description = "CRUD of Material")
@RestController
@RequestMapping("material")
@Validated
public class MaterialController {

    private MaterialService materialService;

    @Autowired
    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @Operation(summary = "get the material")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MaterialDTO> findAll() {
        return  materialService.findAll();
    }

    @Operation(summary = "get the material by ID")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MaterialDTO findById(@PathVariable Long id) throws MaterialNotFoundException {
        return materialService.findById(id);
    }

    @Operation(summary = "creating the material")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MaterialDTO save(@Valid @RequestBody CreateMaterialDTO data) {
        return materialService.save(data);
    }

    @Operation(summary = "updating the material")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MaterialDTO update(@Valid  @PathVariable Long id, @RequestBody UpdateMaterialDTO data) throws MaterialNotFoundException {
        return materialService.update(id, data);
    }

    @Operation(summary = "deleting the material")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws MaterialNotFoundException {
        materialService.deleteById(id);
    }
}