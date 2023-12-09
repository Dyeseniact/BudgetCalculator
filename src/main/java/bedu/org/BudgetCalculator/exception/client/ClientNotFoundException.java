package bedu.org.BudgetCalculator.exception.client;

import bedu.org.BudgetCalculator.exception.RuntimeException;

public class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException(long clientId) {
        super("ERR_DATA_NOT_FOUND", "Client not found with ID: ", clientId);
    }
}
