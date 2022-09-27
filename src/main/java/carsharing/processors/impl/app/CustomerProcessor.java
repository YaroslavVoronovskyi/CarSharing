package carsharing.processors.impl.app;

import carsharing.ConsoleReader;
import carsharing.Constants;
import carsharing.model.Customer;
import carsharing.processors.ICustomerProcessors;
import carsharing.processors.ICustomerProcessorsFactory;
import carsharing.processors.IAppActionProcessor;
import carsharing.service.ICustomerService;

import java.util.List;

public class CustomerProcessor implements IAppActionProcessor {

    private final ICustomerService customerService;
    private final ICustomerProcessorsFactory customerProcessorsFactory;

    public CustomerProcessor(ICustomerService customerService, ICustomerProcessorsFactory customerProcessorsFactory) {
        this.customerService = customerService;
        this.customerProcessorsFactory = customerProcessorsFactory;
    }

    @Override
    public boolean doAction() {
        List<Customer> customersList = customerService.getAll();
        if (customersList.size() == 0) {
            System.out.println(Constants.CUSTOMERS_LIST_EMPTY_MESSAGE);
            return true;
        }

        System.out.println("Choose a customer:");
        int index = 0;
        for (Customer customer : customersList) {
            index++;
            System.out.println(index + Constants.DOT_SEPARATOR + customer.getName() + Constants.DELIMETER);
        }

        int customerId = ConsoleReader.getIntFromConsole(index);

        if (customerId == 0) {
            return true;
        }

        boolean needContinue = true;
        while (needContinue) {
            Customer customer = customersList.get(customerId - 1);
            String customerActionTitle = ConsoleReader.getStringFromConsole(
                    Constants.QUOT + customer.getName() + Constants.QUOT_WITH_COLON + Constants.CUSTOMER_PROCESSOR_MENU_MESSAGE);
            ICustomerProcessors customerProcessors = null;
            while (customerProcessors == null) {
                customerProcessors = customerProcessorsFactory.getCustomerProcessorByAction(customerActionTitle);
                if (customerProcessors == null) {
                    System.out.println(Constants.WRONG_TITLE_ERROR);
                    System.out.println(Constants.QUOT + customer.getName() + Constants.QUOT_WITH_COLON
                            + Constants.CUSTOMER_PROCESSOR_MENU_MESSAGE);
                    customerActionTitle = ConsoleReader.getStringFromConsole(Constants.CORRECT_TITLE_MESSAGE);
                }
            }
            customerProcessorsFactory.getCustomerProcessorByAction(customerActionTitle);
            needContinue = customerProcessors.doActionWithCustomer(customer.getId());
        }
        return true;
    }

    @Override
    public String getSupportedActionTitle() {
        return "2";
    }
}
