package carsharing.processors.impl.customer;

import carsharing.model.Customer;
import carsharing.processors.ICustomerProcessors;
import carsharing.service.ICarService;
import carsharing.service.ICustomerService;

public class ReturnRentedCarProcessor implements ICustomerProcessors {

    private final ICustomerService customerService;
    public final ICarService carService;

    public ReturnRentedCarProcessor(ICustomerService customerService, ICarService carService) {
        this.customerService = customerService;
        this.carService = carService;
    }

    @Override
    public boolean doActionWithCustomer(int customerId) {
        Customer customer = customerService.getById(customerId);
        if (customer.getRentedCarId() <= 0) {
            System.out.println("You didn't rent a car!");
            return true;
        }
        customer.setRentedCarId(null);
        customerService.updateCustomerLeftRentedCar(customer);
        System.out.println("You've returned a rented car!");
        return true;
    }

    @Override
    public String getSupportedCustomerActionTitle() {
        return "2";
    }
}
