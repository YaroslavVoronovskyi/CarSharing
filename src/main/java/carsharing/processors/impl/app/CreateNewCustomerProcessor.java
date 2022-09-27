package carsharing.processors.impl.app;

import carsharing.ConsoleReader;
import carsharing.Constants;
import carsharing.model.Customer;
import carsharing.processors.IAppActionProcessor;
import carsharing.service.ICustomerService;

public class CreateNewCustomerProcessor implements IAppActionProcessor {

    private final ICustomerService customerService;

    public CreateNewCustomerProcessor(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public boolean doAction() {
        String name = ConsoleReader.getStringFromConsole("Enter the customer name:", Constants.NAME_CHECK_PATTERN,
                Constants.WRONG_NAME_FORMAT_ERROR);
        Customer customer = new Customer(name);
        customerService.save(customer);
        System.out.println("The customer was created!");
        return true;
    }

    @Override
    public String getSupportedActionTitle() {
        return "3";
    }
}
