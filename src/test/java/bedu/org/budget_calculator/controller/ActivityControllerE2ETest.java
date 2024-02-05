package bedu.org.budget_calculator.controller;

import bedu.org.budget_calculator.dto.activity.ActivityDTO;
import bedu.org.budget_calculator.model.Activity;
import bedu.org.budget_calculator.repository.ActivityRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest
class ActivityControllerE2ETest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ActivityRepository repository;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("GET /activity/{id} should return an error if the activity is not found")
    void activityNotFoundTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/activity/" + 5333333L))
                .andExpect(status().isNotFound())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        String expectedResponse = "{\"code\":\"ERR_DATA_NOT_FOUND\",\"message\":\"Activity not found with ID: \",\"details\":5333333}";

        assertEquals(expectedResponse, content);
    }

    @Test
    @DisplayName("POST /activity should return an error if name is missing")
    void nameMissingInRequestBodyTest() throws Exception {
        mockMvc.perform(post("/activity").contentType("application/json").content("{\"unit\":\"Test unit\"}"))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    @DisplayName("POST /activity should return an error if unit is missing")
    void unitMissingInRequestBodyTest() throws Exception {
        mockMvc.perform(post("/activity").contentType("application/json").content("{\"name\":\"Test name\"}"))
                .andExpect(status().isBadRequest())
                .andReturn();
    }


    @Test
    @DisplayName("PATCH /activity/{id} should return an error if the activity is not found")
    void updateActivityNotFoundTest() throws Exception {
        ActivityDTO updatedActivity = new ActivityDTO();
        updatedActivity.setName("Test name");
        updatedActivity.setUnit("Test unit");

        mockMvc.perform(patch("/activity/" + 5333333L).contentType("application/json").content(mapper.writeValueAsString(updatedActivity)))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("PATCH /activity/{id} should return an error if name is empty")
    void updateActivityNameEmptyTest() throws Exception {
        ActivityDTO updatedActivity = new ActivityDTO();
        updatedActivity.setName("");
        updatedActivity.setUnit("Test unit");

        mockMvc.perform(patch("/activity/" + 20L).contentType("application/json").content(mapper.writeValueAsString(updatedActivity)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @DisplayName("PATCH /activity/{id} should return an error if unit is empty")
    void updateActivityUnitEmptyTest() throws Exception {
        ActivityDTO updatedActivity = new ActivityDTO();
        updatedActivity.setName("Test name");
        updatedActivity.setUnit("");

        mockMvc.perform(patch("/activity/" + 20L).contentType("application/json").content(mapper.writeValueAsString(updatedActivity)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @DisplayName("GET /activity should return a list of activities")
    void findAllTest() throws Exception {
        Activity existingActivity = new Activity();
        existingActivity.setName("Test name");
        existingActivity.setUnit("Test unit");

        Activity savedActivity = repository.save(existingActivity);

        MvcResult result = mockMvc.perform(get("/activity"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertTrue(content.contains(savedActivity.getName()));
        assertTrue(content.contains(savedActivity.getUnit()));
    }

    @Test
    @DisplayName("DELETE /activity/{id} should return an error if the activity is not found")
    void deleteActivityNotFoundTest() throws Exception {
        mockMvc.perform(delete("/activity/" + 5333333L))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    @DisplayName("DELETE /activity/{id} should delete an activity successfully")
    void deleteActivityTest() throws Exception {
        Activity existingActivity = new Activity();
        existingActivity.setName("Test name");
        existingActivity.setUnit("Test unit");

        Activity savedActivity = repository.save(existingActivity);

        mockMvc.perform(delete("/activity/" + savedActivity.getId()))
                .andExpect(status().isNoContent());

        assertFalse(repository.existsById(savedActivity.getId()));
    }

}
