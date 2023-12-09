package bedu.org.BudgetCalculator.mapper;

import bedu.org.BudgetCalculator.dto.CreateMaterialDTO;
import bedu.org.BudgetCalculator.dto.MaterialDTO;
import bedu.org.BudgetCalculator.dto.UpdateMaterialDTO;
import bedu.org.BudgetCalculator.model.Material;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MaterialMapper {
    MaterialDTO toDTO(Material model);

    @Mapping(target = "id", ignore = true)
    Material toModel(CreateMaterialDTO dto);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Material material, UpdateMaterialDTO data);
}
