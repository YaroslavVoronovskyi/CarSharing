package carsharing.processors.impl.app;

import carsharing.ConsoleReader;
import carsharing.Constants;
import carsharing.model.Customer;
import carsharing.processors.ICustomerProcessor;
import carsharing.processors.ICustomerProcessorsFactory;
import carsharing.processors.IAppActionProcessor;
import carsharing.service.ICustomerService;

import java.util.List;

public class LoginAsCustomerProcessor implements IAppActionProcessor {

    private final ICustomerService customerService;
    private final ICustomerProcessorsFactory customerProcessorsFactory;

    public LoginAsCustomerProcessor(ICustomerService customerService, ICustomerProcessorsFactory customerProcessorsFactory) {
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
        Customer customer = customersList.get(customerId - 1);
        boolean needContinue = true;
        while (needContinue) {
            String customerActionTitle = ConsoleReader.getStringFromConsole(
                    Constants.QUOTE + customer.getName() + Constants.QUOTE_WITH_COLON + Constants.CUSTOMER_PROCESSOR_MENU_MESSAGE);
            ICustomerProcessor customerProcessor = null;
            while (customerProcessor == null) {
                customerProcessor = customerProcessorsFactory.getCustomerProcessorByAction(customerActionTitle);
                if (customerProcessor == null) {
                    System.out.println(Constants.WRONG_TITLE_ERROR);
                    System.out.println(Constants.QUOTE + customer.getName() + Constants.QUOTE_WITH_COLON
                            + Constants.CUSTOMER_PROCESSOR_MENU_MESSAGE);
                    customerActionTitle = ConsoleReader.getStringFromConsole(Constants.CORRECT_TITLE_MESSAGE);
                }
            }
            needContinue = customerProcessor.doActionWithCustomer(customer);
        }
        return true;
    }

    @Override
    public String getSupportedActionTitle() {
        return "2";
    }
}
