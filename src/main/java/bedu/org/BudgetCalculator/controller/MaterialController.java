package bedu.org.BudgetCalculator.controller;

import bedu.org.BudgetCalculator.dto.material.CreateMaterialDTO;
import bedu.org.BudgetCalculator.dto.material.MaterialDTO;
import bedu.org.BudgetCalculator.exception.material.MaterialNotFoundException;
import bedu.org.BudgetCalculator.service.MaterialService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("material")
@Validated
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MaterialDTO> findAll() {
        return  materialService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MaterialDTO findById(@PathVariable Long id) throws MaterialNotFoundException {
        return materialService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MaterialDTO saveMaterial(@Valid @RequestBody CreateMaterialDTO data) {
        return materialService.save(data);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public MaterialDTO updateMaterial(@Valid  @PathVariable Long id, @RequestBody CreateMaterialDTO data) throws MaterialNotFoundException {
        return materialService.update(id,data);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMaterial(@PathVariable Long id) throws MaterialNotFoundException {
        materialService.delete(id);
    }
}
