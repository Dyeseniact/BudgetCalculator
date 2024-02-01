package bedu.org.budget_calculator.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import bedu.org.budget_calculator.dto.activity.ActivityDTO;
import bedu.org.budget_calculator.dto.activity.CreateActivityDTO;
import bedu.org.budget_calculator.dto.activity.UpdateActivityDTO;
import bedu.org.budget_calculator.model.Activity;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ActivityMapper {
    ActivityDTO toDTO(Activity model);

    List<ActivityDTO> toDTO(List<Activity> model);

    @Mapping(target = "id", ignore = true)
    Activity toModel(CreateActivityDTO dto);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Activity activity, UpdateActivityDTO data);
}
