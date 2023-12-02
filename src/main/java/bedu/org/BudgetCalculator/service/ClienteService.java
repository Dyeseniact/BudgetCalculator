package bedu.org.BudgetCalculator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bedu.org.BudgetCalculator.dto.ClienteDTO;
import bedu.org.BudgetCalculator.dto.CreateClienteDTO;
import bedu.org.BudgetCalculator.mapper.ClienteMapper;
import bedu.org.BudgetCalculator.model.Cliente;
import bedu.org.BudgetCalculator.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private ClienteMapper mapper;

    public List<ClienteDTO> findAll() {
        List<Cliente> data = repository.findAll();
        return data.stream().map(mapper::toDTO).toList();
    }
    
    public ClienteDTO save(CreateClienteDTO data) {
        Cliente entity = repository.save(mapper.toModel(data));
        return mapper.toDTO(entity);
    }

    public ClienteDTO update(Long id, CreateClienteDTO data) {
        // Verificar si el cliente con el ID proporcionado existe
        Cliente existingCliente = repository.findById(id).orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
        
        // Asignar los datos actualizados al nuevo cliente
        existingCliente.setName(data.getName());
        existingCliente.setApellido(data.getApellido());
        existingCliente.setTelefono(data.getTelefono());
        existingCliente.setEmail(data.getEmail());

        // Actualizar el cliente enla base de datos
        Cliente updatedCliente = repository.save(existingCliente);

        // Devolver los datos del cliente actualizado
        return mapper.toDTO(updatedCliente);
    }

    public void delete(Long id) {
        // Verificar si el cliente con el ID proporcionado existe
        Cliente existingCliente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
        // Eliminar el cliente de la base de datos
        repository.delete(existingCliente);
    }
}
