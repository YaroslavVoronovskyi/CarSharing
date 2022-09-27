package carsharing.processors;

public interface ICustomerProcessors {

    boolean doActionWithCustomer(int customerId);

    String getSupportedCustomerActionTitle();
}
