package carsharing.processors.impl.customer;

import carsharing.processors.ICustomerProcessors;
import carsharing.processors.ICustomerProcessorsFactory;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CustomerProcessorsFactory implements ICustomerProcessorsFactory {

    private final Map<String, ICustomerProcessors> customerProcessorsMap;

    public CustomerProcessorsFactory(List<ICustomerProcessors> customerProcessorsList) {
        customerProcessorsMap = customerProcessorsList.stream()
                .collect(Collectors.toMap(ICustomerProcessors::getSupportedCustomerActionTitle, Function.identity()));
    }

    @Override
    public ICustomerProcessors getCustomerProcessorByAction(String customerActionTitle) {
        return customerProcessorsMap.get(customerActionTitle);
    }
}
