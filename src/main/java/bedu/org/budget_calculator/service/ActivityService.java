package bedu.org.budget_calculator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bedu.org.budget_calculator.dto.activity.ActivityDTO;
import bedu.org.budget_calculator.dto.activity.CreateActivityDTO;
import bedu.org.budget_calculator.dto.activity.UpdateActivityDTO;
import bedu.org.budget_calculator.exception.activity.ActivityNotFoundException;
import bedu.org.budget_calculator.mapper.ActivityMapper;
import bedu.org.budget_calculator.model.Activity;
import bedu.org.budget_calculator.repository.ActivityRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {

    private ActivityRepository activityRepository;
    private ActivityMapper activityMapper;


    //  Error creating bean with name 'activityController' - Al tener un constructor por inyección.
    //  Se corrige unificado todos en un mismo constructor.
    @Autowired
    public ActivityService(ActivityRepository activityRepository, ActivityMapper activityMapper) {
        this.activityRepository = activityRepository;
        this.activityMapper = activityMapper;
    }

    public List<ActivityDTO> findAll(){
        return activityMapper.toDTO(activityRepository.findAll());
    }

    public ActivityDTO findById(Long id) throws ActivityNotFoundException {
        Optional<Activity> result = activityRepository.findById(id);

        if(!result.isPresent()){
            throw new ActivityNotFoundException(id);
        }

        return activityMapper.toDTO(result.get());
    }

    public ActivityDTO save(CreateActivityDTO data){
        Activity entity = activityRepository.save(activityMapper.toModel(data));
        return activityMapper.toDTO(entity);
    }

    public ActivityDTO update(Long id, UpdateActivityDTO data) throws ActivityNotFoundException {
        Optional<Activity> result = activityRepository.findById(id);

        if(!result.isPresent()){
            throw new ActivityNotFoundException(id);
        }

        Activity activity = result.get();
        activity.setName(data.getName());
        activity.setUnit(data.getUnit());

        Activity updated = activityRepository.save(activity);

        return activityMapper.toDTO(updated);
    }

    public void delete(Long id) throws ActivityNotFoundException {
        Optional<Activity> result = activityRepository.findById(id);

        if(!result.isPresent()){
            throw new ActivityNotFoundException(id);
        }

        activityRepository.deleteById(id);
    }
}
