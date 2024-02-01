package bedu.org.budget_calculator.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bedu.org.budget_calculator.dto.client.ClientDTO;
import bedu.org.budget_calculator.dto.client.CreateClientDTO;
import bedu.org.budget_calculator.exception.client.ClientNotFoundException;
import bedu.org.budget_calculator.service.ClientService;
import jakarta.validation.Valid;

@Tag(name = "Endpoint of Client",  description = "CRUD of Client")
@RestController
@RequestMapping("client")
public class ClientController {

    @Autowired
    private ClientService service;

    // Get all client
    @Operation(summary = "get the client")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDTO> findAll() {
        return service.findAll();
    }

    // Get client by id
    @Operation(summary = "get the client by ID")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClientDTO findById(@PathVariable Long id) throws ClientNotFoundException {
        return service.findById(id);
    }

    // Create a new client
    @Operation(summary = "creating the client")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDTO save(@Valid @RequestBody CreateClientDTO data) {
        return service.save(data);
    }

    // Update an existing client
    @Operation(summary = "updating the client")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDTO updateClient(@Valid @PathVariable Long id, @RequestBody CreateClientDTO data) throws ClientNotFoundException {
        return service.update(id, data);
    }

    // Delete an existing client
    @Operation(summary = "deleting the client")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCliente(@PathVariable Long id) throws ClientNotFoundException {
        service.delete(id);
    }

}
