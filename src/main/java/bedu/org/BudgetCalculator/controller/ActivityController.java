package bedu.org.BudgetCalculator.controller;

import bedu.org.BudgetCalculator.dto.activity.ActivityDTO;
import bedu.org.BudgetCalculator.dto.activity.CreateActivityDTO;
import bedu.org.BudgetCalculator.dto.activity.UpdateActivityDTO;
import bedu.org.BudgetCalculator.exception.activity.ActivityNotFoundException;
import bedu.org.BudgetCalculator.service.ActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import bedu.org.BudgetCalculator.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "updating the budget")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody UpdateActivityDTO data) throws ActivityNotFoundException, ValidationException {
        if (data.getName() != null && data.getName().isEmpty()) {
            throw new ValidationException("Name cannot be empty");
        }
        if (data.getUnit() != null && data.getUnit().isEmpty()) {
            throw new ValidationException("Unit cannot be empty");
        }
        activityService.update(id, data);
    }
    @Operation(summary = "deleting the budget")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws ActivityNotFoundException {
        activityService.delete(id);
    }
}
