package bedu.org.BudgetCalculator.service;

import bedu.org.BudgetCalculator.dto.Customer.CreateCustomerDTO;
import bedu.org.BudgetCalculator.dto.Customer.CustomerDTO;
import bedu.org.BudgetCalculator.mappers.CustomerMapper;
import bedu.org.BudgetCalculator.model.Customer;
import bedu.org.BudgetCalculator.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;

    @Autowired
    private CustomerMapper mapper;

    public List<CustomerDTO> findAll() {
        List<Customer> data = repository.findAll();
        return data.stream().map(mapper::toDTO).toList();
    }

    public CustomerDTO save(CreateCustomerDTO data) {
        Customer entity = repository.save(mapper.toModel(data));
        return mapper.toDTO(entity);
    }

    /*
     * Para el caso de actualizar cliente creo que vendría bien un nuevo DTO, porque al usar
     * el de creación ambos valores son obligatorios cuando deberían ser opcionales.
     */
    public CustomerDTO update(Long id, CreateCustomerDTO data) {
        // Verificar si el cliente con el ID proporcionado existe
        Customer existingCustomer = repository.findById(id).orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));

        // Asignar los datos actualizados al nuevo cliente
        existingCustomer.setName(data.getName());
        existingCustomer.setApellido(data.getApellido());
        existingCustomer.setTelefono(data.getTelefono());
        existingCustomer.setEmail(data.getEmail());

        // Actualizar el Customer enla base de datos
        Customer updatedCustomer = repository.save(existingCustomer);

        // Devolver los datos del cliente actualizado
        return mapper.toDTO(updatedCustomer);
    }

    public void delete(Long id) {
        // Verificar si el cliente con el ID proporcionado existe
        Customer existingCustomer = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
        // Eliminar el cliente de la base de datos
        repository.delete(existingCustomer);
    }
}
