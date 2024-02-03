package bedu.org.budget_calculator.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import bedu.org.budget_calculator.dto.material.CreateMaterialDTO;
import bedu.org.budget_calculator.dto.material.MaterialDTO;
import bedu.org.budget_calculator.dto.material.UpdateMaterialDTO;
import bedu.org.budget_calculator.exception.material.MaterialNotFoundException;
import bedu.org.budget_calculator.service.MaterialService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.LinkedList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class MaterialControllerTest {

    @MockBean
    private MaterialService service;

    @Autowired
    private MaterialController controller;

    @Test
    @DisplayName("Controller should be injected")
    void smokeTest() {
        assertNotNull(controller);
    }

    @Test
    @DisplayName("Controller should return a list of materials")
    void findAllTest() {
        List<MaterialDTO> data = new LinkedList<>();

        MaterialDTO material = new MaterialDTO();

        material.setId(1);
        material.setName("Madera");
        material.setPrice(100.0);
        material.setQuantity(50);

        data.add(material);

        when(service.findAll()).thenReturn(data);

        List<MaterialDTO> result = controller.findAll();

        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertEquals(material.getId(), result.get(0).getId());
        assertEquals(material.getName(), result.get(0).getName());
        assertEquals(material.getPrice(), result.get(0).getPrice());
        assertEquals(material.getQuantity(), result.get(0).getQuantity());
    }

    @Test
    @DisplayName("Controller should find a material by ID")
    void findByIdTest() throws MaterialNotFoundException {

        MaterialDTO material = new MaterialDTO();
        material.setId(1L);
        material.setName("Example Material");
        material.setQuantity(100);
        material.setPrice(10.0);

        when(service.findById(material.getId())).thenReturn(material);

        MaterialDTO result = controller.findById(material.getId());

        assertEquals(material.getId(), result.getId());
        assertEquals(material.getName(), result.getName());
        assertEquals(material.getQuantity(), result.getQuantity());
        assertEquals(material.getPrice(), result.getPrice());
    }

    @Test
    @DisplayName("Controller should handle MaterialNotFoundException when material is not found by ID")
    void findByIdNotFoundTest() throws MaterialNotFoundException {

        when(service.findById(100L)).thenThrow(MaterialNotFoundException.class);

        assertThrows(MaterialNotFoundException.class, () -> controller.findById(100L));
    }

    @Test
    @DisplayName("Controller should save a material")
    void saveTest() {
        CreateMaterialDTO dto = new CreateMaterialDTO();

        dto.setName("Pintura Morada");
        dto.setPrice(400.0);
        dto.setQuantity(60);

        MaterialDTO material = new MaterialDTO();

        material.setId(10);
        material.setName(dto.getName());
        material.setPrice(dto.getPrice());
        material.setQuantity(dto.getQuantity());


        when(service.save(any(CreateMaterialDTO.class))).thenReturn(material);

        MaterialDTO result = controller.save(dto);

        assertNotNull(result);
        assertEquals(material.getId(), result.getId());
        assertEquals(material.getName(), result.getName());
        assertEquals(material.getPrice(), result.getPrice());
        assertEquals(material.getQuantity(), result.getQuantity());
    }

    @Test
    @DisplayName("Controller should update a material")
    void updateTest() throws MaterialNotFoundException {
        UpdateMaterialDTO dto = new UpdateMaterialDTO();

        dto.setName("Pintura Azul");
        dto.setPrice(400.0);
        dto.setQuantity(300);

        controller.update(2L, dto);

        verify(service, times(1)).update(2L, dto);
    }

    @Test
    @DisplayName("Controller should delete a material")
    void deleteByIdTest() throws MaterialNotFoundException {
        controller.delete(3L);

        verify(service, times(1)).deleteById(3L);
    }
}
