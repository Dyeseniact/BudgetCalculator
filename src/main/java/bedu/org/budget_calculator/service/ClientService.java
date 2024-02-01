package bedu.org.budget_calculator.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bedu.org.budget_calculator.dto.client.ClientDTO;
import bedu.org.budget_calculator.dto.client.CreateClientDTO;
import bedu.org.budget_calculator.exception.client.ClientNotFoundException;
import bedu.org.budget_calculator.mapper.ClientMapper;
import bedu.org.budget_calculator.model.Client;
import bedu.org.budget_calculator.repository.ClientRepository;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Autowired
    private ClientMapper mapper;

    public List<ClientDTO> findAll() {
        List<Client> data = repository.findAll();
        return data.stream().map(mapper::toDTO).toList();
    }

    public ClientDTO findById(Long id) throws ClientNotFoundException {
        Optional<Client> result = repository.findById(id);

        if(!result.isPresent()){
            throw new ClientNotFoundException(id);
        }

        return mapper.toDTO(result.get());
    }

    public ClientDTO save(CreateClientDTO data) {
        System.out.println("entra al save");
        System.out.println("datos de entrada: " + data);
        System.out.println("pasamos el mapper dto a modelo: " + mapper.toModel(data));
        Client entity = repository.save(mapper.toModel(data));
        System.out.println("se crea un entity de cliente: " + entity);
        System.out.println("la respuesta que va a enviar: " + mapper.toDTO(entity));
        return mapper.toDTO(entity);
    }

    public ClientDTO update(Long id, CreateClientDTO data) throws ClientNotFoundException {
        Client existingCliente = repository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));

        existingCliente.setName(data.getName());
        existingCliente.setLastname(data.getLastname());
        existingCliente.setPhone(data.getPhone());
        existingCliente.setEmail(data.getEmail());

        Client updatedCliente = repository.save(existingCliente);

        return mapper.toDTO(updatedCliente);
    }

    public void delete(Long id) throws ClientNotFoundException {
        Client existingCliente = repository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));
        repository.delete(existingCliente);
    }
}
