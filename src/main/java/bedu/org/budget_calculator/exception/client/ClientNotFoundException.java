package bedu.org.budget_calculator.exception.client;

import bedu.org.budget_calculator.exception.ResourceNotFoundException;

public class ClientNotFoundException extends ResourceNotFoundException {
    public ClientNotFoundException(long clientId) {
        super("Client", clientId);
    }
}

