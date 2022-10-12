package carsharing.processors.impl.customer;

import carsharing.model.Customer;
import carsharing.processors.ICustomerProcessor;

public class BackFromCustomerMenuToPreviousMenuProcessor implements ICustomerProcessor {
    @Override
    public boolean doActionWithCustomer(Customer customer) {
        return false;
    }

    @Override
    public String getSupportedCustomerActionTitle() {
        return "0";
    }
}
