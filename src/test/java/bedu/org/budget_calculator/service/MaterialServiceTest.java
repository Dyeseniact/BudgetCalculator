package bedu.org.budget_calculator.service;

import bedu.org.budget_calculator.dto.material.CreateMaterialDTO;
import bedu.org.budget_calculator.dto.material.MaterialDTO;
import bedu.org.budget_calculator.dto.material.UpdateMaterialDTO;
import bedu.org.budget_calculator.exception.material.MaterialNotFoundException;
import bedu.org.budget_calculator.model.Material;
import bedu.org.budget_calculator.repository.MaterialRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class MaterialServiceTest {

    @MockBean
    private MaterialRepository repository;

    @Autowired
    private MaterialService service;

    @Test
    @DisplayName("Service should be injected")
    void smokeTest() {
        assertNotNull(service);
    }

    @Test
    @DisplayName("Service should return materials from repository")
    void findAllTest() {
        List<Material> data = new LinkedList<>();

        Material material = new Material();

        material.setId(20);
        material.setName("Clavos");
        material.setQuantity(9000);
        material.setPrice(3.0);

        data.add(material);

        when(repository.findAll()).thenReturn(data);

        List<MaterialDTO> result = service.findAll();

        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertEquals(material.getId(), result.get(0).getId());
        assertEquals(material.getName(), result.get(0).getName());
        assertEquals(material.getPrice(), result.get(0).getPrice());
        assertEquals(material.getQuantity(), result.get(0).getQuantity());

    }

    @Test
    @DisplayName("Service should find material by id")
    void findByIdTest() throws MaterialNotFoundException {

        Material material = new Material();

        material.setId(1L);
        material.setName("Example Material");
        material.setQuantity(100);
        material.setPrice(10.0);

        when(repository.findById(material.getId())).thenReturn(Optional.of(material));

        MaterialDTO result = service.findById(material.getId());

        assertEquals(material.getId(), result.getId());
        assertEquals(material.getName(), result.getName());
        assertEquals(material.getQuantity(), result.getQuantity());
        assertEquals(material.getPrice(), result.getPrice());
    }

    @Test
    @DisplayName("Service should throw MaterialNotFoundException when material is not found")
    void findByIdNotFoundTest() {
        when(repository.findById(1965L)).thenReturn(Optional.empty());

        assertThrows(MaterialNotFoundException.class, () -> service.findById(1965L));
    }

    @Test
    @DisplayName("Service should save a material in repository")
    void saveTest() {
        CreateMaterialDTO dto = new CreateMaterialDTO();

        dto.setName("Martillo");
        dto.setQuantity(300);
        dto.setPrice(80.0);

        Material model = new Material();

        model.setId(5L);
        model.setName(dto.getName());
        model.setQuantity(dto.getQuantity());
        model.setPrice(dto.getPrice());

        when(repository.save(any(Material.class))).thenReturn(model);

        MaterialDTO result = service.save(dto);

        assertNotNull(result);
        assertEquals(model.getId(), result.getId());
        assertEquals(model.getName(), result.getName());
        assertEquals(model.getQuantity(), result.getQuantity());
        assertEquals(model.getPrice(),result.getPrice());
    }

    @Test
    @DisplayName("Service should throw an error if material was not found")
    void updateWithErrorTest() {
        UpdateMaterialDTO dto = new UpdateMaterialDTO();
        Optional<Material> dummy = Optional.empty();

        when(repository.findById(anyLong())).thenReturn(dummy);

        assertThrows(MaterialNotFoundException.class, () -> service.update(100L, dto));
    }
    @Test
    @DisplayName("Service should update a material in repository")
    void updateTest() throws MaterialNotFoundException {
        UpdateMaterialDTO dto = new UpdateMaterialDTO();

        dto.setName("Pala");
        dto.setQuantity(500);
        dto.setPrice(150.0);

        Material material = new Material();

        material.setId(40L);
        material.setName("Pala de acero");
        material.setQuantity(800);
        material.setPrice(190.0);

        when(repository.findById(anyLong())).thenReturn(Optional.of(material));

        service.update(40L, dto);

        assertEquals(dto.getName(), material.getName());
        assertEquals(dto.getQuantity(), material.getQuantity());
        assertEquals(dto.getPrice(), material.getPrice());
        verify(repository, times(1)).save(material);
    }

    @Test
    @DisplayName("Service should delete a material by id in repository")
    void deleteByIdTest() {
        Long materialId = 1L;

        assertThrows(MaterialNotFoundException.class, () -> {
            service.deleteById(materialId);
        });

        verify(repository, never()).deleteById(anyLong());
    }
}
