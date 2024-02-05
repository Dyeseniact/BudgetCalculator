package bedu.org.budget_calculator.controller;

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

import com.fasterxml.jackson.databind.ObjectMapper;

import bedu.org.budget_calculator.dto.client.ClientDTO;
import bedu.org.budget_calculator.model.Client;
import bedu.org.budget_calculator.repository.ClientRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest
class ClientControllerE2ETest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClientRepository repository;

    private ObjectMapper mapper = new ObjectMapper();

    // Get by id Error message
    @Test
    @DisplayName("GET /client/{id} should return an error if the client is not found")
    void clientNotFoundTest() throws Exception {

        MvcResult result = mockMvc.perform(get("/client/" + 7777777))
                .andExpect(status().isNotFound())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        String expectedResponse = "{\"code\":\"ERR_DATA_NOT_FOUND\",\"message\":\"Client not found with ID: \",\"details\":7777777}";

        assertEquals(expectedResponse, content);
    }

    // Create a new client Error missing name
    @Test
    @DisplayName("POST /client should return an error if name is missing")
    void nameMissingInRequestBodyTest() throws Exception {
        MvcResult result = mockMvc.perform(post("/client").contentType("application/json").content("{\"lastname\":\"Garcia\", \"phone\":\"1234567890\", \"email\":\"raul.garcia@example.com\"}"))
                .andExpect(status().isBadRequest())
                .andReturn();

        String content = result.getResponse().getContentAsString();


        String expectedResponse = "{\"code\":\"ERR_VALID\",\"message\":\"A error ocurred procesando input data\",\"details\":[\"Name must not be blank\"]}";

        assertEquals(expectedResponse, content);
    }

    @Test
    @DisplayName("PUT /client/{id} should update a client successfully")
    void updateClientTest() throws Exception {
        Client existingClient = new Client();
        existingClient.setName("Raul");
        existingClient.setLastname("Garcia");
        existingClient.setPhone("1234567890");
        existingClient.setEmail("raul.garcia@example.com");
        Client savedClient = repository.save(existingClient);

        ClientDTO updatedClientDTO = new ClientDTO();
        updatedClientDTO.setName("NameUpdated");
        updatedClientDTO.setLastname("LastnameUpdated");
        updatedClientDTO.setPhone("1234567899");
        updatedClientDTO.setEmail("new.email@example.com");

        mockMvc.perform(put("/client/" + savedClient.getId())
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(updatedClientDTO)))
                .andExpect(status().isOk());

        Client updatedClient = repository.findById(savedClient.getId()).orElseThrow();
        assertEquals(updatedClientDTO.getName(), updatedClient.getName());
        assertEquals(updatedClientDTO.getLastname(), updatedClient.getLastname());
        assertEquals(updatedClientDTO.getPhone(), updatedClient.getPhone());
        assertEquals(updatedClientDTO.getEmail(), updatedClient.getEmail());
    }

    @Test
    @DisplayName("PUT /client/{id} should return 404 if client not found")
    void updateClientNotFoundTest() throws Exception {
        ClientDTO updatedClientDTO = new ClientDTO();
        updatedClientDTO.setName("NonExistingClient");
        updatedClientDTO.setLastname("Lastname");
        updatedClientDTO.setPhone("1234567890");
        updatedClientDTO.setEmail("email@example.com");

        mockMvc.perform(put("/Client/" + 7777777L)
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(updatedClientDTO)))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("DELETE /client/{id} should delete a client successfully")
    void deleteClientTest() throws Exception {
        Client existingClient = new Client();
        existingClient.setName("Raul");
        existingClient.setLastname("Garcia");
        existingClient.setPhone("1234567890");
        existingClient.setEmail("raul.garcia@example.com");
        Client savedClient = repository.save(existingClient);

        mockMvc.perform(delete("/client/" + savedClient.getId()))
                .andExpect(status().isNoContent());

        assertFalse(repository.existsById(savedClient.getId()));
    }

}
