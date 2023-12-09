package bedu.org.BudgetCalculator.service;

import bedu.org.BudgetCalculator.dto.ActivityDTO;
import bedu.org.BudgetCalculator.dto.CreateActivityDTO;
import bedu.org.BudgetCalculator.dto.UpdateActivityDTO;
import bedu.org.BudgetCalculator.mapper.ActivityMapper;
import bedu.org.BudgetCalculator.model.Activity;
import bedu.org.BudgetCalculator.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ActivityMapper activityMapper;

    public List<ActivityDTO> findAll(){
        return activityMapper.toDTO(activityRepository.findAll());
    }

    public ActivityDTO findById(Long id){
        return activityMapper.toDTO(activityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Activity not found :" + id)));
    }

    public ActivityDTO save(CreateActivityDTO data){
        Activity entity = activityRepository.save(activityMapper.toModel(data));
        return activityMapper.toDTO(entity);
    }

    public void update(Long id, UpdateActivityDTO data) {
        Activity existingActivity = activityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Activity not found :" + id));
        existingActivity.setName(data.getName());
        existingActivity.setUnit(data.getUnit());
        activityRepository.save(existingActivity);
    }

    public void delete(Long id) {
        Activity existingActivity = activityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Activity not found :" + id));
        activityRepository.delete(existingActivity);
    }
}
