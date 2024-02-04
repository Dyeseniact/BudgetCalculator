package bedu.org.budget_calculator.controller;

import bedu.org.budget_calculator.dto.client.ClientDTO;
import bedu.org.budget_calculator.dto.concept.ConceptDTO;
import bedu.org.budget_calculator.dto.concept.CreateConceptDTO;
import bedu.org.budget_calculator.dto.concept.UpdateConceptDTO;
import bedu.org.budget_calculator.dto.material.CreateMaterialDTO;
import bedu.org.budget_calculator.dto.material.MaterialDTO;
import bedu.org.budget_calculator.dto.material.UpdateMaterialDTO;
import bedu.org.budget_calculator.exception.concept.ConceptNotFoundException;
import bedu.org.budget_calculator.exception.material.MaterialNotFoundException;
import bedu.org.budget_calculator.model.Concept;
import bedu.org.budget_calculator.service.ConceptService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ConceptControllerTest {

    @MockBean
    private ConceptService service;

    @Autowired
    private ConceptController controller;

    final LocalDate dateStart = LocalDate.of(2024, 2, 1);
    final LocalDate dateEnd = LocalDate.of(2024, 7, 7);

    @Test
    @DisplayName("Controller should be injected")
    void smokeTest() {
        assertNotNull(controller);
    }

    @Test
    @DisplayName("Controller should return a list of concepts")
    void findAllTest() {
        List<ConceptDTO> data = new LinkedList<>();

        ConceptDTO concept = new ConceptDTO();

        concept.setId(7);
        concept.setDescription("Installation of wooden floors.");
        concept.setQuantity(3);
        concept.setUnitPrice(500);
        concept.setSubtotal(1500);
        concept.setStartDate(dateStart);
        concept.setEndDate(dateEnd);

        data.add(concept);

        when(service.findAll()).thenReturn(data);

        List<ConceptDTO> result = controller.findAll();

        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertEquals(concept.getId(), result.get(0).getId());
        assertEquals(concept.getDescription(), result.get(0).getDescription());
        assertEquals(concept.getQuantity(), result.get(0).getQuantity());
        assertEquals(concept.getUnitPrice(), result.get(0).getUnitPrice());
        assertEquals(concept.getSubtotal(), result.get(0).getSubtotal());
        assertEquals(concept.getStartDate(), result.get(0).getStartDate());
        assertEquals(concept.getEndDate(), result.get(0).getEndDate());
    }

    @Test
    @DisplayName("Controller should handle ConceptNotFoundException when material is not found by ID")
    void findByIdNotFoundTest() throws ConceptNotFoundException{

        when(service.findById(100L)).thenThrow(ConceptNotFoundException.class);

        assertThrows(ConceptNotFoundException.class, () -> controller.findById(100L));
    }

    @Test
    @DisplayName("Controller should save a concept")
    void saveTest() {
        CreateConceptDTO dto = new CreateConceptDTO();


        dto.setDescription("Installation of wooden floors");
        dto.setQuantity(6);
        dto.setUnitPrice(500);
        dto.setSubtotal(3000);
        dto.setStartDate( dateStart);
        dto.setEndDate(dateEnd);

        ConceptDTO saved = new ConceptDTO();

        saved.setId(8);
        saved.setDescription("Installation of wooden floors");
        saved.setQuantity(6);
        saved.setUnitPrice(500);
        saved.setSubtotal(3000);
        saved.setStartDate( dateStart);
        saved.setEndDate(dateEnd);

        when(service.save(any(CreateConceptDTO.class))).thenReturn(saved);

        ConceptDTO result = controller.save(dto);

        assertNotNull(result);
        assertEquals(saved.getId(), result.getId());
        assertEquals(saved.getDescription(), result.getDescription());
        assertEquals(saved.getQuantity(), result.getQuantity());
        assertEquals(saved.getUnitPrice(), result.getUnitPrice());
        assertEquals(saved.getSubtotal(), result.getSubtotal());
        assertEquals(saved.getStartDate(), result.getStartDate());
        assertEquals(saved.getEndDate(), result.getEndDate());

    }

    @Test
    @DisplayName("Controller should update a concept")
    void updateTest() throws ConceptNotFoundException {
        UpdateConceptDTO dto = new UpdateConceptDTO();

        dto.setDescription("Installation of wooden floors");
        dto.setQuantity(6);
        dto.setUnitPrice(500);
        dto.setSubtotal(3000);
        dto.setStartDate( dateStart);
        dto.setEndDate(dateEnd);

        controller.update(9L, dto);

        verify(service, times(1)).update(9L, dto);
    }
    @Test
    @DisplayName("Controller should delete a concept")
    void deleteByIdTest() throws ConceptNotFoundException {
        controller.deleteById(9L);

        verify(service, times(1)).deleteById(9L);
    }


}
