package bedu.org.BudgetCalculator.controller;

import bedu.org.BudgetCalculator.dto.ActivityDTO;
import bedu.org.BudgetCalculator.dto.CreateActivityDTO;
import bedu.org.BudgetCalculator.dto.UpdateActivityDTO;
import bedu.org.BudgetCalculator.service.ActivityService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ActivityDTO> findAll(){
        return activityService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ActivityDTO findById(@PathVariable Long id){
        return activityService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ActivityDTO save(@Valid @RequestBody CreateActivityDTO data){
        if (data.getName() == null || data.getUnit() == null) {
            throw new ValidationException("Name and unit are required");
        }

        return activityService.save(data);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody UpdateActivityDTO data){
        if (data.getName() != null && data.getName().isEmpty()) {
            throw new ValidationException("Name cannot be empty");
        }
        if (data.getUnit() != null && data.getUnit().isEmpty()) {
            throw new ValidationException("Unit cannot be empty");
        }
        activityService.update(id, data);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        activityService.delete(id);
    }
}
