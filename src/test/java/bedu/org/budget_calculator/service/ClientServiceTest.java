package bedu.org.budget_calculator.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import bedu.org.budget_calculator.dto.client.ClientDTO;
import bedu.org.budget_calculator.dto.client.CreateClientDTO;
import bedu.org.budget_calculator.exception.client.ClientNotFoundException;
import bedu.org.budget_calculator.mapper.ClientMapper;
import bedu.org.budget_calculator.model.Client;
import bedu.org.budget_calculator.repository.ClientRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository repository;

    @Mock
    private ClientMapper mapper;

    @InjectMocks
    private ClientService service;

    // Get all clients
    @Test
    @DisplayName("Service should return a list of clients")
    void findAllTest() {
        // Mocking repository data
        Client client1 = new Client();
        client1.setId(1L);
        client1.setName("Raul");
        client1.setLastname("Garcia");
        client1.setEmail("raul.garcia@example.com");
        client1.setPhone("1234567890");

        Client client2 = new Client();
        client2.setId(2L);
        client2.setName("Juan");
        client2.setLastname("Perez");
        client2.setEmail("juan.perez@example.com");
        client2.setPhone("9876543210");

        List<Client> fakeData = Arrays.asList(client1, client2);

        when(repository.findAll()).thenReturn(fakeData);

        // Mocking mapper
        ClientDTO clientDTO1 = new ClientDTO();
        clientDTO1.setId(1L);
        clientDTO1.setName("Raul");
        clientDTO1.setLastname("Garcia");
        clientDTO1.setEmail("raul.garcia@example.com");
        clientDTO1.setPhone("1234567890");

        ClientDTO clientDTO2 = new ClientDTO();
        clientDTO2.setId(2L);
        clientDTO2.setName("Juan");
        clientDTO2.setLastname("Perez");
        clientDTO2.setEmail("juan.perez@example.com");
        clientDTO2.setPhone("9876543210");

        when(mapper.toDTO(client1)).thenReturn(clientDTO1);
        when(mapper.toDTO(client2)).thenReturn(clientDTO2);

        // Actual test
        List<ClientDTO> result = service.findAll();

        assertEquals(2, result.size());
        assertEquals(clientDTO1, result.get(0));
        assertEquals(clientDTO2, result.get(1));
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

        when(repository.findById(1L)).thenReturn(Optional.of(client));

        // Mocking mapper
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(1L);
        clientDTO.setName("Raul");
        clientDTO.setLastname("Garcia");
        clientDTO.setEmail("raul.garcia@example.com");
        clientDTO.setPhone("1234567890");

        when(mapper.toDTO(client)).thenReturn(clientDTO);

        // Actual test
        ClientDTO result = service.findById(1L);

        assertEquals(clientDTO, result);
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

        // Mocking mapping
        Client clientEntity = new Client();
        clientEntity.setId(1L);
        clientEntity.setName("Raul");
        clientEntity.setLastname("Garcia");
        clientEntity.setEmail("raul.garcia@example.com");
        clientEntity.setPhone("1234567890");

        when(repository.save(clientEntity)).thenReturn(clientEntity);
        when(mapper.toModel(createClientDTO)).thenReturn(clientEntity);

        ClientDTO result = service.save(createClientDTO);
        
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Raul", result.getName());
        assertEquals("Garcia", result.getLastname());
        assertEquals("raul.garcia@example.com", result.getEmail());
        assertEquals("1234567890", result.getPhone());
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

        when(repository.findById(clientId)).thenReturn(Optional.of(existingClient));

        Client updatedClient = new Client();
        updatedClient.setId(clientId);
        updatedClient.setName("UpdatedName");
        updatedClient.setLastname("UpdatedLastname");
        updatedClient.setEmail("updated.email@example.com");
        updatedClient.setPhone("9876543210");

        when(repository.save(existingClient)).thenReturn(updatedClient);

        // Mocking mapper
        ClientDTO updatedClientDTO = new ClientDTO();
        updatedClientDTO.setId(clientId);
        updatedClientDTO.setName("UpdatedName");
        updatedClientDTO.setLastname("UpdatedLastname");
        updatedClientDTO.setEmail("updated.email@example.com");
        updatedClientDTO.setPhone("9876543210");

        when(mapper.toDTO(updatedClient)).thenReturn(updatedClientDTO);

        // Actual test
        ClientDTO result = service.update(clientId, updateData);

        assertEquals(updatedClientDTO, result);
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
}
