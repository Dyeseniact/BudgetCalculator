package bedu.org.budget_calculator.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import bedu.org.budget_calculator.dto.activity.ActivityDTO;
import bedu.org.budget_calculator.dto.activity.CreateActivityDTO;
import bedu.org.budget_calculator.dto.activity.UpdateActivityDTO;
import bedu.org.budget_calculator.exception.ValidationException;
import bedu.org.budget_calculator.exception.activity.ActivityNotFoundException;
import bedu.org.budget_calculator.service.ActivityService;

import java.util.List;

@Tag(name = "Endpoint of Activity",  description = "CRUD of Activity")
@RestController
@RequestMapping("activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @Operation(summary = "get the activity")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ActivityDTO> findAll(){
        return activityService.findAll();
    }

    @Operation(summary = "get the activity by the ID")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ActivityDTO findById(@PathVariable Long id) throws ActivityNotFoundException {
        return activityService.findById(id);
    }

    @Operation(summary = "creating the activity")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ActivityDTO save(@Valid @RequestBody CreateActivityDTO data) throws ValidationException {
        if (data.getName() == null || data.getUnit() == null) {
            throw new ValidationException("Name and unit are required");
        }

        return activityService.save(data);
    }

    @Operation(summary = "updating activity by the ID")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ActivityDTO update(@PathVariable Long id, @RequestBody UpdateActivityDTO data) throws ActivityNotFoundException, ValidationException {
        if (data.getName() != null && data.getName().isEmpty()) {
            throw new ValidationException("Name cannot be empty");
        }
        if (data.getUnit() != null && data.getUnit().isEmpty()) {
            throw new ValidationException("Unit cannot be empty");
        }
        return activityService.update(id, data);

    }
    @Operation(summary = "deleting activity by the ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws ActivityNotFoundException {
        activityService.delete(id);
    }
}
