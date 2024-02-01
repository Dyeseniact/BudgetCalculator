package bedu.org.budget_calculator.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import bedu.org.budget_calculator.dto.material.CreateMaterialDTO;
import bedu.org.budget_calculator.dto.material.MaterialDTO;
import bedu.org.budget_calculator.dto.material.UpdateMaterialDTO;
import bedu.org.budget_calculator.model.Material;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MaterialMapper {
    MaterialDTO toDTO(Material model);

    @Mapping(target = "id", ignore = true)
    Material toModel(CreateMaterialDTO dto);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Material material, UpdateMaterialDTO data);
}
