package bedu.org.BudgetCalculator.mappers;

import bedu.org.BudgetCalculator.dto.Activity.ActivityDTO;
import bedu.org.BudgetCalculator.dto.Activity.CreateActivityDTO;
import bedu.org.BudgetCalculator.model.Activity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ActivityMapper {
    ActivityDTO toDTO(Activity model);

    List<ActivityDTO> toDTO(List<Activity> model);

    @Mapping(target = "id", ignore = true)
    Activity toModel(CreateActivityDTO dto);
}
