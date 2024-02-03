package bedu.org.budget_calculator.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import bedu.org.budget_calculator.model.Client;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    // Smoke test
    @Test
    @DisplayName("Repository should be injected")
    void smokeTest() {
        assertNotNull(clientRepository);
    }

    // Create a new client
    @Test
    @DisplayName("Repository should save a new client")
    void saveClientTest() {
        Client client = new Client();
        client.setName("Raul");
        client.setLastname("Garcia");
        client.setEmail("raul.garcia@example.com");
        client.setPhone("1234567890");

        clientRepository.save(client);

        assertNotNull(client.getId()); // Check the the ID was generated before saved it. 
    }

    // Get client by id
    @Test
    @DisplayName("Repository should find a client by ID")
    void findClientByIdTest() {
        Client client = new Client();
        client.setName("Raul");
        client.setLastname("Garcia");
        client.setEmail("raul.garcia@example.com");
        client.setPhone("1234567890");

        clientRepository.save(client);

        Long clientId = client.getId();

        assertTrue(clientRepository.existsById(clientId)); // Check that the client exists on the database

        Client retrievedClient = clientRepository.findById(clientId).orElse(null);

        assertNotNull(retrievedClient);
        assertEquals("Raul", retrievedClient.getName());
        assertEquals("Garcia", retrievedClient.getLastname());
        assertEquals("raul.garcia@example.com", retrievedClient.getEmail());
        assertEquals("1234567890", retrievedClient.getPhone());
    }

    // Delete an existing client
    @Test
    @DisplayName("Repository should delete a client by ID")
    void deleteClientByIdTest() {
        Client client = new Client();
        client.setName("Raul");
        client.setLastname("Garcia");
        client.setEmail("raul.garcia@example.com");
        client.setPhone("1234567890");

        clientRepository.save(client);

        Long clientId = client.getId();

        assertTrue(clientRepository.existsById(clientId)); // Check that the client exists on the database

        clientRepository.deleteById(clientId);

        assertFalse(clientRepository.existsById(clientId)); // Check that the client has been deleted
    }

    // Update an existing client
    @Test
    @DisplayName("Repository should update an existing client")
    void updateClientTest() {
        // Save a client on the database
        Client client = new Client();
        client.setName("Raul");
        client.setLastname("Garcia");
        client.setEmail("raul.garcia@example.com");
        client.setPhone("1234567890");

        clientRepository.save(client);

        // Update data of the existing client
        Long clientId = client.getId();
        client.setName("UpdatedName");
        client.setEmail("updated.email@example.com");

        // Update the client on the database
        clientRepository.save(client);

        // Get the updated client from the database
        Client updatedClient = clientRepository.findById(clientId).orElse(null);

        assertNotNull(updatedClient);
        assertEquals("UpdatedName", updatedClient.getName());
        assertEquals("Garcia", updatedClient.getLastname());
        assertEquals("updated.email@example.com", updatedClient.getEmail());
        assertEquals("1234567890", updatedClient.getPhone());
    }
}
