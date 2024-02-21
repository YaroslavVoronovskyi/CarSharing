package carsharing.processors.impl.customer;

import carsharing.model.Customer;
import carsharing.processors.ICustomerProcessor;
import carsharing.service.ICustomerService;

public class ReturnRentedCarProcessor implements ICustomerProcessor {

    private final ICustomerService customerService;

    public ReturnRentedCarProcessor(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public boolean doActionWithCustomer(Customer customer) {
        if (customer.getRentedCarId() == null) {
            System.out.println("You didn't rent a car!");
            return true;
        }
        customer.setRentedCarId(null);
        customerService.update(customer);
        System.out.println("You've returned a rented car!");
        return true;
    }

    @Override
    public String getSupportedCustomerActionTitle() {
        return "2";
    }
}
