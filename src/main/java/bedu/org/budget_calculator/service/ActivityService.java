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

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ActivityMapper activityMapper;

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

    public void update(Long id, UpdateActivityDTO data) throws ActivityNotFoundException {
        Optional<Activity> result = activityRepository.findById(id);

        if(!result.isPresent()){
            throw new ActivityNotFoundException(id);
        }

        Activity activity = result.get();
        activityMapper.update(activity, data);
        activityRepository.save(activity);
    }

    public void delete(Long id) throws ActivityNotFoundException {
        Optional<Activity> result = activityRepository.findById(id);

        if(!result.isPresent()){
            throw new ActivityNotFoundException(id);
        }

        activityRepository.deleteById(id);

    }
}
