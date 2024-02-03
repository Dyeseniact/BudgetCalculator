package bedu.org.budget_calculator.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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
import bedu.org.budget_calculator.model.Client;
import bedu.org.budget_calculator.repository.ClientRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @MockBean
    private ClientRepository repository;

    @Autowired
    private ClientService service;

    // Smoke test
    @Test
    @DisplayName("Service should be injected")
    void smokeTest() {
        assertNotNull(service);
    }

    // Get all clients
    @Test
    @DisplayName("Service should return a list of clients from repository")
    void findAllTest() {
        List<Client> data= new LinkedList<>();

        // Mocking repository data
        Client client1 = new Client();
        client1.setId(1L);
        client1.setName("Raul");
        client1.setLastname("Garcia");
        client1.setEmail("raul.garcia@example.com");
        client1.setPhone("1234567890");
        data.add(client1);

        Client client2 = new Client();
        client2.setId(2L);
        client2.setName("Juan");
        client2.setLastname("Perez");
        client2.setEmail("juan.perez@example.com");
        client2.setPhone("9876543210");
        data.add(client2);

        when(repository.findAll()).thenReturn(data);

        // Actual test
        List<ClientDTO> result = service.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(client1.getId(), result.get(0).getId());
        assertEquals(client1.getName(), result.get(0).getName());
        assertEquals(client1.getLastname(), result.get(0).getLastname());
        assertEquals(client1.getEmail(), result.get(0).getEmail());
        assertEquals(client1.getPhone(), result.get(0).getPhone());
        assertEquals(client2.getId(), result.get(1).getId());
        assertEquals(client2.getName(), result.get(1).getName());
        assertEquals(client2.getLastname(), result.get(1).getLastname());
        assertEquals(client2.getEmail(), result.get(1).getEmail());
        assertEquals(client2.getPhone(), result.get(1).getPhone());

    }

    // Get client by id
    @Test
    @DisplayName("Service should return a client by ID")
    void findByIdTest() throws ClientNotFoundException {
        // Mocking repository data
        Client client = new Client();

        client.setId(1L);
        client.setName("Raul");
        client.setLastname("Garcia");
        client.setEmail("raul.garcia@example.com");
        client.setPhone("1234567890");

        when(repository.findById(client.getId())).thenReturn(Optional.of(client));

        // Actual test
        ClientDTO result = service.findById(client.getId());

        // assertEquals(client, result);
        assertEquals(client.getId(), result.getId());
        assertEquals(client.getName(), result.getName());
        assertEquals(client.getLastname(), result.getLastname());
        assertEquals(client.getEmail(), result.getEmail());
        assertEquals(client.getPhone(), result.getPhone());
    }
    
    @Test
    @DisplayName("Service should throw ClientNotFoundException when client is not found by ID")
    void findByIdNotFoundTest() {
        // Mocking input data
        Long clientId = 1L;

        // Mocking repository behavior
        when(repository.findById(clientId)).thenReturn(Optional.empty());

        // Actual test
        assertThrows(ClientNotFoundException.class, () -> service.findById(clientId));
    }

    // Create a new client
    @Test
    @DisplayName("Service should save a new client")
    void saveTest() {
        // Mocking input data
        CreateClientDTO createClientDTO = new CreateClientDTO();
        createClientDTO.setName("Raul");
        createClientDTO.setLastname("Garcia");
        createClientDTO.setEmail("raul.garcia@example.com");
        createClientDTO.setPhone("1234567890");

        // Mocking entity
        Client clientEntity = new Client();
        clientEntity.setId(1L);
        clientEntity.setName("Raul");
        clientEntity.setLastname("Garcia");
        clientEntity.setEmail("raul.garcia@example.com");
        clientEntity.setPhone("1234567890");

        when(repository.save(any(Client.class))).thenReturn(clientEntity);

        ClientDTO result = service.save(createClientDTO);
        
        assertNotNull(result);
        assertEquals( clientEntity.getId(), result.getId());
        assertEquals(clientEntity.getName(), result.getName());
        assertEquals(clientEntity.getLastname(), result.getLastname());
        assertEquals(clientEntity.getEmail(), result.getEmail());
        assertEquals(clientEntity.getPhone(), result.getPhone());
    }

    // Update an existing client
    @Test
    @DisplayName("Service should update an existing client")
    void updateClientTest() throws ClientNotFoundException {
        Long clientId = 1L;

        // Mocking repository data
        CreateClientDTO updateData = new CreateClientDTO();
        updateData.setName("UpdatedName");
        updateData.setLastname("UpdatedLastname");
        updateData.setEmail("updated.email@example.com");
        updateData.setPhone("9876543210");

        Client existingClient = new Client();
        existingClient.setId(clientId);
        existingClient.setName("Raul");
        existingClient.setLastname("Garcia");
        existingClient.setEmail("raul.garcia@example.com");
        existingClient.setPhone("1234567890");

        when(repository.findById(anyLong())).thenReturn(Optional.of(existingClient));

        // Mocking the save operation
        when(repository.save(any(Client.class))).thenAnswer(invocation -> {
            // Return the modified client (simulating the saved entity)
            Client savedClient = invocation.getArgument(0);
            savedClient.setName(updateData.getName());
            savedClient.setLastname(updateData.getLastname());
            savedClient.setEmail(updateData.getEmail());
            savedClient.setPhone(updateData.getPhone());
            return savedClient;
        });

        // Actual test
        ClientDTO result = service.update(clientId, updateData);

        assertNotNull(result);
        assertEquals(updateData.getName(), result.getName());
        assertEquals(updateData.getLastname(), result.getLastname());
        assertEquals(updateData.getEmail(), result.getEmail());
        assertEquals(updateData.getPhone(), result.getPhone());
        verify(repository, times(1)).save(any(Client.class));
    }


    @Test
    @DisplayName("Service should throw an error if client was not found")
    void updateWithErrorTest() {
        CreateClientDTO dto = new CreateClientDTO();
        Optional<Client> dummy = Optional.empty();

        when(repository.findById(anyLong())).thenReturn(dummy);

        assertThrows(ClientNotFoundException.class, () -> service.update(100L, dto));
    }

    // Delete an existing client
    @Test
    @DisplayName("Service should delete an existing client")
    void deleteTest() {
        // Mocking input data
        Long clientId = 1L;

        // Mocking repository behavior
        Client existingClient = new Client();
        existingClient.setId(clientId);
        existingClient.setName("Raul");
        existingClient.setLastname("Garcia");
        existingClient.setEmail("raul.garcia@example.com");
        existingClient.setPhone("1234567890");

        when(repository.findById(clientId)).thenReturn(Optional.of(existingClient));

        // Actual test
        assertDoesNotThrow(() -> service.delete(clientId));

        // Verificar que el método delete del repositorio se haya llamado con el cliente existente
        verify(repository, times(1)).delete(existingClient);
    }

    @Test
    @DisplayName("Service should throw ClientNotFoundException when deleting a non-existent client")
    void deleteNonExistentClientTest() {
        // Mocking input data
        Long clientId = 1L;

        // Mocking repository behavior
        when(repository.findById(clientId)).thenReturn(Optional.empty());

        // Actual test
        assertThrows(ClientNotFoundException.class, () -> service.delete(clientId));

        // Check that delete method is never invoke
        verify(repository, never()).delete(any(Client.class));
    }
}
