package carsharing.processors.impl.customer;

import carsharing.processors.ICustomerProcessors;

public class BackFromCustomerMenuToPreviousMenuProcessor implements ICustomerProcessors {
    @Override
    public boolean doActionWithCustomer(int customerId) {
        return false;
    }

    @Override
    public String getSupportedCustomerActionTitle() {
        return "0";
    }
}
