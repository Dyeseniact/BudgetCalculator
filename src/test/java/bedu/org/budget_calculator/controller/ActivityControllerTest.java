package bedu.org.budget_calculator.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import bedu.org.budget_calculator.dto.activity.CreateActivityDTO;
import bedu.org.budget_calculator.dto.activity.UpdateActivityDTO;
import bedu.org.budget_calculator.dto.activity.ActivityDTO;
import bedu.org.budget_calculator.exception.ValidationException;
import bedu.org.budget_calculator.exception.activity.ActivityNotFoundException;
import bedu.org.budget_calculator.service.ActivityService;

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
public class ActivityControllerTest {

    @MockBean
    private ActivityService service;

    @Autowired
    private ActivityController controller;

    @Test
    @DisplayName("Controller should be injected")
    void smokeTest() {
        assertNotNull(controller);
    }

    @Test
    @DisplayName("Controller should return a list of activities")
    void findAllTest() {
        List<ActivityDTO> data = new LinkedList<>();

        ActivityDTO activity = new ActivityDTO();

        activity.setId(20);
        activity.setName("changing the electric cable");
        activity.setUnit("2");

        data.add(activity);

        when(service.findAll()).thenReturn(data);

        List<ActivityDTO> result = controller.findAll();

        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertEquals(activity.getId(), result.get(0).getId());
        assertEquals(activity.getName(), result.get(0).getName());
        assertEquals(activity.getUnit(), result.get(0).getUnit());
    }

    @Test
    @DisplayName("Controller should return a single activity")
    void findByIdTest() throws ActivityNotFoundException {
        ActivityDTO activity = new ActivityDTO();

        activity.setId(20);
        activity.setName("changing the electric cable");
        activity.setUnit("2");

        when(service.findById(activity.getId())).thenReturn(activity);

        ActivityDTO result = controller.findById(activity.getId());

        assertNotNull(result);
        assertEquals(activity.getId(), result.getId());
        assertEquals(activity.getName(), result.getName());
        assertEquals(activity.getUnit(), result.getUnit());
    }

    @Test
    @DisplayName("Controller should save a new activity")
    void saveTest() throws ValidationException {
        CreateActivityDTO data = new CreateActivityDTO();

        data.setName("changing the electric cable");
        data.setUnit("2");

        ActivityDTO activity = new ActivityDTO();

        activity.setId(20);
        activity.setName("changing the electric cable");
        activity.setUnit("2");

        when(service.save(data)).thenReturn(activity);

        ActivityDTO result = controller.save(data);

        assertNotNull(result);
        assertEquals(activity.getId(), result.getId());
        assertEquals(activity.getName(), result.getName());
        assertEquals(activity.getUnit(), result.getUnit());
    }

    @Test
    @DisplayName("Controller should throw ValidationException if name or unit are null when saving")
    void saveValidationExceptionTest() {
        CreateActivityDTO data = new CreateActivityDTO();

        assertThrows(ValidationException.class, () -> {
            controller.save(data);
        });
    }

    @Test
    @DisplayName("Controller should update an existing activity")
    void updateTest() throws ActivityNotFoundException, ValidationException {
        UpdateActivityDTO data = new UpdateActivityDTO();

        data.setName("changing the electric cable");
        data.setUnit("2");

        ActivityDTO activity = new ActivityDTO();

        activity.setId(20);
        activity.setName("changing the electric cable");
        activity.setUnit("2");

        when(service.update(activity.getId(), data)).thenReturn(activity);

        ActivityDTO result = controller.update(activity.getId(), data);

        assertNotNull(result);
        assertEquals(activity.getId(), result.getId());
        assertEquals(activity.getName(), result.getName());
        assertEquals(activity.getUnit(), result.getUnit());
    }

    @Test
    @DisplayName("Controller should fail to update an existing activity")
    void updateExceptionTest() throws ActivityNotFoundException {
        UpdateActivityDTO data = new UpdateActivityDTO();

        when(service.update(20L, data)).thenThrow(ActivityNotFoundException.class);

        assertThrows(ActivityNotFoundException.class, () -> {
            controller.update(20L, data);
        });
    }

    @Test
    @DisplayName("Controller should throw ValidationException if name or unit are empty when updating")
    void updateValidationExceptionTest() throws ActivityNotFoundException {
        UpdateActivityDTO data = new UpdateActivityDTO();
        data.setName("");
        data.setUnit("");

        assertThrows(ValidationException.class, () -> {
            controller.update(20L, data);
        });
    }

    @Test
    @DisplayName("Controller should delete an existing activity")
    void deleteTest() throws ActivityNotFoundException {
        Long id = 20L;

        doNothing().when(service).delete(id);

        controller.delete(id);

        verify(service, times(1)).delete(id);
    }

}
