package carsharing;

import carsharing.dao.ICarDao;
import carsharing.dao.ICompanyDao;
import carsharing.dao.ICustomerDao;
import carsharing.dao.impl.CarDao;
import carsharing.dao.impl.CompanyDao;
import carsharing.dao.impl.CustomerDao;
import carsharing.processors.ICustomerProcessor;
import carsharing.processors.ICarProcessor;
import carsharing.processors.IAppActionProcessor;
import carsharing.processors.ICompanyProcessor;
import carsharing.processors.ICustomerProcessorsFactory;
import carsharing.processors.ICompanyProcessorsFactory;
import carsharing.processors.ICarProcessorsFactory;
import carsharing.processors.IAppProcessorsFactory;
import carsharing.processors.impl.car.BackFromCarMenuToPreviousMenuProcessor;
import carsharing.processors.impl.car.CarProcessorsFactory;
import carsharing.processors.impl.car.CreateNewCarProcessor;
import carsharing.processors.impl.car.ShowCarsListProcessor;
import carsharing.processors.impl.company.BackFromCompanyMenuToPreviousMenuProcessor;
import carsharing.processors.impl.company.CreateNewCompanyProcessor;
import carsharing.processors.impl.company.CompanyProcessorFactory;
import carsharing.processors.impl.company.ShowCompaniesListProcessor;
import carsharing.processors.impl.customer.CustomerProcessorsFactory;
import carsharing.processors.impl.customer.ReturnRentedCarProcessor;
import carsharing.processors.impl.customer.RentNewCarProcessor;
import carsharing.processors.impl.customer.ShowCustomerRentedCarProcessor;
import carsharing.processors.impl.customer.BackFromCustomerMenuToPreviousMenuProcessor;
import carsharing.processors.impl.app.AppProcessorsFactory;
import carsharing.processors.impl.app.LoginAsCustomerProcessor;
import carsharing.processors.impl.app.LoginAsManagerProcessor;
import carsharing.processors.impl.app.ExitApplicationProcessor;
import carsharing.processors.impl.app.CreateNewCustomerProcessor;
import carsharing.service.ICarService;
import carsharing.service.ICompanyService;
import carsharing.service.ICustomerService;
import carsharing.service.impl.CarService;
import carsharing.service.impl.CompanyService;
import carsharing.service.impl.CustomerService;

import java.util.List;

public class Application {

    public static void main(String[] args) {

        Initializer.innitDataBase();

        ICompanyDao companyDao = new CompanyDao();
        ICarDao carDao = new CarDao();
        ICustomerDao customerDao = new CustomerDao();

        ICompanyService companyService = new CompanyService(companyDao);
        ICarService carService = new CarService(carDao);
        ICustomerService customerService = new CustomerService(customerDao);

        ICustomerProcessor rentNewCarProcessor = new RentNewCarProcessor(companyService, carService, customerService);
        ICustomerProcessor returnRentedCarProcessor = new ReturnRentedCarProcessor(customerService, carService);
        ICustomerProcessor showCustomerRentedCarProcessor = new ShowCustomerRentedCarProcessor(carService, companyService);
        ICustomerProcessor backFromCustomerMenuToPreviousMenuProcessor = new BackFromCustomerMenuToPreviousMenuProcessor();

        ICustomerProcessorsFactory customerProcessorsFactory = new CustomerProcessorsFactory(
                List.of(rentNewCarProcessor, returnRentedCarProcessor, showCustomerRentedCarProcessor,
                        backFromCustomerMenuToPreviousMenuProcessor));

        ICarProcessor showCarsListProcessor = new ShowCarsListProcessor(carService);
        ICarProcessor createNewCarProcessor = new CreateNewCarProcessor(carService);
        ICarProcessor backFromCarMenuToPreviousMenuProcessor = new BackFromCarMenuToPreviousMenuProcessor();

        ICarProcessorsFactory carProcessorsFactory = new CarProcessorsFactory(
                List.of(showCarsListProcessor, createNewCarProcessor, backFromCarMenuToPreviousMenuProcessor));

        ICompanyProcessor showCompaniesListProcessor = new ShowCompaniesListProcessor(companyService, carProcessorsFactory);
        ICompanyProcessor createNewCompanyProcessor = new CreateNewCompanyProcessor(companyService);
        ICompanyProcessor backFromCompanyMenuToPreviousMenuProcessor = new BackFromCompanyMenuToPreviousMenuProcessor();

        ICompanyProcessorsFactory companyProcessorsFactory = new CompanyProcessorFactory(
                List.of(showCompaniesListProcessor, createNewCompanyProcessor, backFromCompanyMenuToPreviousMenuProcessor));

        IAppActionProcessor loginAsManagerProcessor = new LoginAsManagerProcessor(companyProcessorsFactory);
        IAppActionProcessor loginAsCustomerProcessor = new LoginAsCustomerProcessor(customerService, customerProcessorsFactory);
        IAppActionProcessor createNewCustomerProcessor = new CreateNewCustomerProcessor(customerService);
        IAppActionProcessor exitApplicationProcessor = new ExitApplicationProcessor();

        IAppProcessorsFactory appProcessorsFactory = new AppProcessorsFactory(
                List.of(loginAsManagerProcessor, exitApplicationProcessor, loginAsCustomerProcessor, createNewCustomerProcessor));

        CarSharing carSharing = new CarSharing(appProcessorsFactory);
        carSharing.runCarSharing();
    }
}