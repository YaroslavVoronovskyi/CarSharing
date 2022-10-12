package carsharing.processors;

public interface ICustomerProcessorsFactory {

    ICustomerProcessor getCustomerProcessorByAction(String customerActionTitle);
}
