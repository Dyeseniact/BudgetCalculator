package bedu.org.BudgetCalculator.controller;

import bedu.org.BudgetCalculator.dto.Customer.CreateCustomerDTO;
import bedu.org.BudgetCalculator.dto.Customer.CustomerDTO;
import bedu.org.BudgetCalculator.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cliente")
public class CustomerController {
    @Autowired
    private CustomerService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerDTO> findAll() {
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO save(@Valid @RequestBody CreateCustomerDTO data) {
        return service.save(data);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO updateCliente(@Valid @PathVariable Long id, @RequestBody CreateCustomerDTO data) {
        return service.update(id, data);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCliente(@PathVariable Long id) {
        service.delete(id);
    }
}
