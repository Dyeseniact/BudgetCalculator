package bedu.org.budget_calculator.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import bedu.org.budget_calculator.dto.client.ClientDTO;
import bedu.org.budget_calculator.dto.client.CreateClientDTO;
import bedu.org.budget_calculator.exception.client.ClientNotFoundException;
import bedu.org.budget_calculator.service.ClientService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ClientControllerTest {

    @MockBean
    private ClientService service;

    @Autowired
    private ClientController controller;

    // Smoke test
    @Test
    @DisplayName("Controller should be injected")
    void smokeTest() {
        assertNotNull(controller);
    }

    // Get all client
    @Test
    @DisplayName("Controller should return a list of clients")
    void findAllTest() {

        List<ClientDTO> fakeData = new LinkedList<>();

        ClientDTO fakeClient = new ClientDTO();

        fakeClient.setId(1L);
        fakeClient.setName("Raul");
        fakeClient.setLastname("Garcia");
        fakeClient.setEmail("raul.garcia@example.com");
        fakeClient.setPhone("1234567890");

        fakeData.add(fakeClient);

        when(service.findAll()).thenReturn(fakeData);

        List<ClientDTO> result = controller.findAll();

        assertEquals(fakeData, result);
    }

    // Get client by id
    @Test
    @DisplayName("Controller should return a client by ID")
    void findByIdTest() throws ClientNotFoundException {

        ClientDTO fakeClient = new ClientDTO();

        fakeClient.setId(1L);
        fakeClient.setName("Raul");
        fakeClient.setLastname("Garcia");
        fakeClient.setEmail("raul.garcia@example.com");
        fakeClient.setPhone("1234567890");

        when(service.findById(1L)).thenReturn(fakeClient);

        ClientDTO result = controller.findById(1L);

        assertEquals(fakeClient, result);
    }

    @Test
    @DisplayName("Controller should handle ClientNotFoundException for findById")
    void findByIdNotFoundTest() throws ClientNotFoundException {
        when(service.findById(anyLong())).thenThrow(new ClientNotFoundException(777));

        assertThrows(ClientNotFoundException.class, () -> controller.findById(123L));
    }

    // Create a new client
    @Test
    @DisplayName("Controller should save a new client")
    void saveTest() {

        CreateClientDTO dto = new CreateClientDTO();

        dto.setName("Raul");
        dto.setLastname("Garcia");
        dto.setEmail("raul.garcia@example.com");
        dto.setPhone("1234567890");

        ClientDTO saved = new ClientDTO();

        saved.setId(777);
        saved.setName("Raul");
        saved.setLastname("Garcia");
        saved.setEmail("raul.garcia@example.com");
        saved.setPhone("1234567890");

        when(service.save(any(CreateClientDTO.class))).thenReturn(saved);

        ClientDTO result = controller.save(dto);

        assertNotNull(result);
        assertEquals(dto.getName(), result.getName());
        assertEquals(dto.getLastname(), result.getLastname());
        assertEquals(dto.getEmail(), result.getEmail());
        assertEquals(dto.getPhone(), result.getPhone());
    }

    // Update an existing client
    @Test
    @DisplayName("Controller should update an existing client")
    void updateClientTest() throws ClientNotFoundException {

        Long clientId = 999L;

        CreateClientDTO updateData = new CreateClientDTO();
        updateData.setName("UpdatedName");

        ClientDTO fakeClient = new ClientDTO();
        fakeClient.setId(clientId);
        fakeClient.setName("UpdatedName");
        fakeClient.setLastname("UpdatedLastname");
        fakeClient.setEmail("updated.email@example.com");
        fakeClient.setPhone("9876543210");

        when(service.update(clientId, updateData)).thenReturn(fakeClient);

        ClientDTO result = controller.updateClient(clientId, updateData);

        assertEquals(fakeClient, result);
    }

    // Delete an existing client
    @Test
    @DisplayName("Controller should delete an existing client")
    void deleteClienteTest() throws ClientNotFoundException {
        Long clientId = 123L;

        controller.deleteCliente(clientId);

        verify(service, times(1)).delete(clientId);
    }

}
