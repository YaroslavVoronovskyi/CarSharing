package carsharing.processors.impl.customer;

import carsharing.processors.ICustomerProcessor;
import carsharing.processors.ICustomerProcessorsFactory;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CustomerProcessorsFactory implements ICustomerProcessorsFactory {

    private final Map<String, ICustomerProcessor> customerProcessorsMap;

    public CustomerProcessorsFactory(List<ICustomerProcessor> customerProcessorsList) {
        customerProcessorsMap = customerProcessorsList.stream()
                .collect(Collectors.toMap(ICustomerProcessor::getSupportedCustomerActionTitle, Function.identity()));
    }

    @Override
    public ICustomerProcessor getCustomerProcessorByAction(String customerActionTitle) {
        return customerProcessorsMap.get(customerActionTitle);
    }
}
