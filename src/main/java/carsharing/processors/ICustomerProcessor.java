package carsharing.processors;

import carsharing.model.Customer;

public interface ICustomerProcessor {

    boolean doActionWithCustomer(Customer customer);

    String getSupportedCustomerActionTitle();
}
