package carsharing.processors.impl.customer;

import carsharing.model.Car;
import carsharing.model.Company;
import carsharing.model.Customer;
import carsharing.processors.ICustomerProcessor;
import carsharing.service.ICarService;
import carsharing.service.ICompanyService;

public class ShowCustomerRentedCarProcessor implements ICustomerProcessor {

    private final ICarService carService;
    private final ICompanyService companyService;

    public ShowCustomerRentedCarProcessor(ICarService carService, ICompanyService companyService) {
        this.carService = carService;
        this.companyService = companyService;
    }

    @Override
    public boolean doActionWithCustomer(Customer customer) {
        if (customer.getRentedCarId() == null) {
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
