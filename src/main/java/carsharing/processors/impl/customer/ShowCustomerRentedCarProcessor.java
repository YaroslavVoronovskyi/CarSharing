package carsharing.processors.impl.customer;

import carsharing.model.Car;
import carsharing.model.Company;
import carsharing.model.Customer;
import carsharing.processors.ICustomerProcessors;
import carsharing.service.ICarService;
import carsharing.service.ICompanyService;
import carsharing.service.ICustomerService;

public class ShowCustomerRentedCarProcessor implements ICustomerProcessors {

    private final ICustomerService customerService;
    private final ICarService carService;
    private final ICompanyService companyService;

    public ShowCustomerRentedCarProcessor(ICustomerService customerService, ICarService carService,
                                          ICompanyService companyService) {
        this.customerService = customerService;
        this.carService = carService;
        this.companyService = companyService;
    }

    @Override
    public boolean doActionWithCustomer(int customerId) {
        Customer customer = customerService.getById(customerId);
        if (customer.getRentedCarId() <= 0) {
            System.out.println("You didn't rent a car!");
            return true;
        }

        Car car = carService.getById(customer.getRentedCarId());
        System.out.println("Car: " + car.getName());

        Company company = companyService.getById(car.getCompanyId());
        System.out.println("Company: " + company.getName());
        return true;
    }


    @Override
    public String getSupportedCustomerActionTitle() {
        return "3";
    }
}
