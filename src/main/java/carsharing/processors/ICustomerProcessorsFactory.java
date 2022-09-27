package carsharing.processors;

public interface ICustomerProcessorsFactory {

    ICustomerProcessors getCustomerProcessorByAction(String customerActionTitle);
}
