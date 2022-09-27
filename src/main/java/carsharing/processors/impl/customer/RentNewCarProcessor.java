package carsharing.processors.impl.customer;

import carsharing.ConsoleReader;
import carsharing.Constants;
import carsharing.model.Car;
import carsharing.model.Company;
import carsharing.model.Customer;
import carsharing.processors.ICustomerProcessors;
import carsharing.service.ICarService;
import carsharing.service.ICompanyService;
import carsharing.service.ICustomerService;

import java.util.List;

public class RentNewCarProcessor implements ICustomerProcessors {

    private final ICompanyService companyService;
    private final ICarService carService;
    private final ICustomerService customerService;

    public RentNewCarProcessor(ICompanyService companyService, ICarService carService, ICustomerService customerService) {
        this.companyService = companyService;
        this.carService = carService;
        this.customerService = customerService;
    }

    @Override
    public boolean doActionWithCustomer(int customerId) {
        List<Company> companiesList = companyService.getAll();
        if (companiesList.size() == 0) {
            System.out.println("You didn't rent a car!");
            return true;
        }

        Customer customer = customerService.getById(customerId);
        if (customer.getRentedCarId() > 0) {
            System.out.println("You've already rented a car!");
            return true;
        }

        System.out.println("Choose a company: ");
        int index = 0;
        for (Company company : companiesList) {
            index++;
            System.out.println(index + Constants.DOT_SEPARATOR + company.getName());
        }

        int companyId = ConsoleReader.getIntFromConsole(index);
        Company currentCompany = companiesList.get(companyId - 1);

        List<Car> carsList = carService.getAllCarsByCompanyId(currentCompany.getId());
        if (carsList.size() == 0) {
            System.out.println(Constants.CARS_LIST_EMPTY_MESSAGE);
            return true;
        }

        System.out.println("Choose a car: ");
        int indexCar = 0;
        for (Car car : carsList) {
            indexCar++;
            System.out.println(indexCar + Constants.DOT_SEPARATOR + car.getName());
        }

        int carId = ConsoleReader.getIntFromConsole(indexCar);
        Car car = carsList.get(carId - 1);

        customer.setRentedCarId(car.getId());
        car.setRented(true);
        carService.update(car);
        customerService.update(customer);
        System.out.println("You rented '" + car.getName() + "'");
        return true;
    }

    @Override
    public String getSupportedCustomerActionTitle() {
        return "1";
    }
}
