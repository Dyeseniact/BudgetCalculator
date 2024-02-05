package bedu.org.budget_calculator.service;

import bedu.org.budget_calculator.dto.concept.ConceptDTO;
import bedu.org.budget_calculator.dto.concept.CreateConceptDTO;
import bedu.org.budget_calculator.dto.concept.UpdateConceptDTO;
import bedu.org.budget_calculator.exception.concept.ConceptNotFoundException;
import bedu.org.budget_calculator.model.Concept;
import bedu.org.budget_calculator.repository.ConceptRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ConceptServiceTest {

    @MockBean
    private ConceptRepository repository;

    @Autowired
    private ConceptService service;

    final LocalDate dateStart = LocalDate.of(2024, 4, 8);
    final LocalDate dateEnd = LocalDate.of(2024, 4, 28);

    @Test
    @DisplayName("Concept should be injected")
    void smokeTest() {
        assertNotNull(service);
    }

    @Test
    @DisplayName("Service should return a list of concepts")
    void findAllTest() {
        List<Concept> data = new LinkedList<>();

        Concept concept = new Concept();

        concept.setId(8L);
        concept.setDescription("Installation of wooden floors");
        concept.setQuantity(3);
        concept.setUnitPrice(500);
        concept.setSubtotal(1500);
        concept.setStartDate(dateStart);
        concept.setEndDate(dateEnd);

        data.add(concept);
        when(repository.findAll()).thenReturn(data);

        List<ConceptDTO> result = service.findAll();

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
    @DisplayName("Service should save a concept in repository")
    void saveTest() {
        CreateConceptDTO dto = new CreateConceptDTO();

        dto.setDescription("Installation of wooden floors");
        dto.setQuantity(3);
        dto.setSubtotal(1500);
        dto.setUnitPrice(500);
        dto.setStartDate(dateStart);
        dto.setEndDate(dateEnd);

        Concept model = new Concept();
        model. setId(90);
        model.setDescription("Installation of wooden floors");
        model.setQuantity(3);
        model.setSubtotal(1500);
        model.setUnitPrice(500);
        model.setStartDate(dateStart);
        model.setEndDate(dateEnd);

        when(repository.save(any(Concept.class))).thenReturn(model);

        ConceptDTO result = service.save(dto);

        assertNotNull(result);
        assertEquals(model.getId(), result.getId());
        assertEquals(model.getDescription(), result.getDescription());
        assertEquals(model.getQuantity(), result.getQuantity());
        assertEquals(model.getSubtotal(), result.getSubtotal());
        assertEquals(model.getUnitPrice(), result.getUnitPrice());
        assertEquals(model.getStartDate(), result.getStartDate());
        assertEquals(model.getEndDate(), result.getEndDate());
    }
    @Test
    @DisplayName("Service should throw an error if concept was not found")
    void  updateWithErrorTest() {
        UpdateConceptDTO dto = new UpdateConceptDTO();
        Optional<Concept> dummy = Optional.empty();

        when(repository.findById(anyLong())).thenReturn(dummy);

        assertThrows(ConceptNotFoundException.class, () -> service.update(100L, dto));
    }

    @Test
    @DisplayName("Service should update a concept in repository")
    void updateTest() throws ConceptNotFoundException {
        UpdateConceptDTO dto = new UpdateConceptDTO();

        dto.setDescription("Installation of wooden floors");
        dto.setQuantity(3);
        dto.setSubtotal(1500);
        dto.setUnitPrice(500);
        dto.setStartDate(dateStart);
        dto.setEndDate(dateEnd);

        Concept concept = new Concept();

        concept.setId(8L);
        concept.setDescription("Installation of wooden floors");
        concept.setQuantity(3);
        concept.setUnitPrice(500);
        concept.setSubtotal(1500);
        concept.setStartDate(dateStart);
        concept.setEndDate(dateEnd);

        when(repository.findById(anyLong())).thenReturn(Optional.of(concept));
        when(repository.save(any(Concept.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Call the update method
        service.update(8L, dto);

        assertEquals(dto.getDescription(), concept.getDescription());
        assertEquals(dto.getQuantity(), concept.getQuantity());
        assertEquals(dto.getUnitPrice(), concept.getUnitPrice());
        assertEquals(dto.getSubtotal(), concept.getSubtotal());
        assertEquals(dto.getStartDate(), concept.getStartDate());
        assertEquals(dto.getEndDate(), concept.getEndDate());

        verify(repository, times(1)).save(concept);

    }

    @Test
    @DisplayName("Service should delete a concept by id in repository")
    void deleteByIdTest() {
        Long conceptId = 1L;

        assertThrows(ConceptNotFoundException.class, () -> {
            service.deleteById(conceptId);
        });

        verify(repository, never()).deleteById(anyLong());
    }
}


