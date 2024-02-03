package bedu.org.budget_calculator.service;

import bedu.org.budget_calculator.dto.activity.CreateActivityDTO;
import bedu.org.budget_calculator.dto.activity.UpdateActivityDTO;
import bedu.org.budget_calculator.dto.activity.ActivityDTO;
import bedu.org.budget_calculator.exception.activity.ActivityNotFoundException;
import bedu.org.budget_calculator.model.Activity;
import bedu.org.budget_calculator.repository.ActivityRepository;

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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ActivityServiceTest {

    @MockBean
    private ActivityRepository repository;

    @Autowired
    private ActivityService service;

    @Test
    @DisplayName("Service should be injected")
    void smokeTest() {
        assertNotNull(service);
    }

    @Test
    @DisplayName("Service should return activities from repository")
    void findAllTest() {
        List<Activity> data = new LinkedList<>();

        Activity activity = new Activity();

        activity.setId(20);
        activity.setName("changing the electric cable");
        activity.setUnit("2");

        data.add(activity);

        when(repository.findAll()).thenReturn(data);

        List<ActivityDTO> result = service.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());

        ActivityDTO dto = result.get(0);

        assertEquals(activity.getId(), dto.getId());
        assertEquals(activity.getName(), dto.getName());
        assertEquals(activity.getUnit(), dto.getUnit());

        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Service should return activity by id from repository")
    void findByIdTest() throws ActivityNotFoundException {
        Activity activity = new Activity();

        activity.setId(20);
        activity.setName("changing the electric cable");
        activity.setUnit("2");

        when(repository.findById(anyLong())).thenReturn(Optional.of(activity));

        ActivityDTO result = service.findById(20L);

        assertNotNull(result);

        assertEquals(activity.getId(), result.getId());
        assertEquals(activity.getName(), result.getName());
        assertEquals(activity.getUnit(), result.getUnit());

        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Service should throw exception when activity not found")
    void findByIdExceptionTest() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ActivityNotFoundException.class, () -> {
            service.findById(20L);
        });

        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Service should save activity")
    void saveTest() {
        CreateActivityDTO dto = new CreateActivityDTO();

        dto.setName("changing the electric cable");
        dto.setUnit("2");

        Activity activity = new Activity();

        activity.setId(20);
        activity.setName("changing the electric cable");
        activity.setUnit("2");

        when(repository.save(any(Activity.class))).thenReturn(activity);

        ActivityDTO result = service.save(dto);

        assertNotNull(result);

        assertEquals(activity.getId(), result.getId());
        assertEquals(activity.getName(), result.getName());
        assertEquals(activity.getUnit(), result.getUnit());

        verify(repository, times(1)).save(any(Activity.class));
    }

    @Test
    @DisplayName("Service should throw exception when activity not found")
    void updateExceptionTest() {
        UpdateActivityDTO dto = new UpdateActivityDTO();

        Optional<Activity> dummy = Optional.empty();

        when(repository.findById(anyLong())).thenReturn(dummy);

        assertThrows(ActivityNotFoundException.class, () -> {
            service.update(20L, dto);
        });

        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Service should update activity")
    void updateTest() throws ActivityNotFoundException {
        UpdateActivityDTO dto = new UpdateActivityDTO();

        dto.setName("changing the electric cable");
        dto.setUnit("2");

        Activity activity = new Activity();

        activity.setId(20);
        activity.setName("changing the electric cable");
        activity.setUnit("2");

        when(repository.findById(anyLong())).thenReturn(Optional.of(activity));

        service.update(20L, dto);

        assertEquals(dto.getName(), activity.getName());
        assertEquals(dto.getUnit(), activity.getUnit());

        verify(repository, times(1)).save(activity);
    }

    @Test
    @DisplayName("Service should delete activity")
    void deleteTest() throws ActivityNotFoundException {
        Activity activity = new Activity();

        activity.setId(20);
        activity.setName("changing the electric cable");
        activity.setUnit("2");

        when(repository.findById(anyLong())).thenReturn(Optional.of(activity));

        service.delete(20L);

        verify(repository, times(1)).deleteById(anyLong());
    }

}

