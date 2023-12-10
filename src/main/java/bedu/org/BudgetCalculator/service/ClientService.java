package bedu.org.BudgetCalculator.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bedu.org.BudgetCalculator.dto.client.ClientDTO;
import bedu.org.BudgetCalculator.dto.client.CreateClientDTO;
import bedu.org.BudgetCalculator.exception.client.ClientNotFoundException;
import bedu.org.BudgetCalculator.mapper.ClientMapper;
import bedu.org.BudgetCalculator.model.Client;
import bedu.org.BudgetCalculator.repository.ClientRepository;

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
        Client entity = repository.save(mapper.toModel(data));
        return mapper.toDTO(entity);
    }

    public ClientDTO update(Long id, CreateClientDTO data) {
        // Verificar si el cliente con el ID proporcionado existe
        Client existingCliente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));

        // Asignar los datos actualizados al nuevo cliente
        existingCliente.setName(data.getName());
        existingCliente.setLastname(data.getLastname());
        existingCliente.setPhone(data.getPhone());
        existingCliente.setEmail(data.getEmail());

        // Actualizar el cliente enla base de datos
        Client updatedCliente = repository.save(existingCliente);

        // Devolver los datos del cliente actualizado
        return mapper.toDTO(updatedCliente);
    }

    public void delete(Long id) {
        // Verificar si el cliente con el ID proporcionado existe
        Client existingCliente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
        // Eliminar el cliente de la base de datos
        repository.delete(existingCliente);
    }
}
