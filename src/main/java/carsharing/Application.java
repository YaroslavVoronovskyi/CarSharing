package carsharing;

import carsharing.dao.ICarDao;
import carsharing.dao.ICompanyDao;
import carsharing.dao.ICustomerDao;
import carsharing.dao.impl.CarDao;
import carsharing.dao.impl.CompanyDao;
import carsharing.dao.impl.CustomerDao;
import carsharing.processors.ICustomerProcessors;
import carsharing.processors.ICarProcessors;
import carsharing.processors.IAppActionProcessor;
import carsharing.processors.ICompanyProcessors;
import carsharing.processors.ICustomerProcessorsFactory;
import carsharing.processors.ICompanyProcessorsFactory;
import carsharing.processors.ICarProcessorsFactory;
import carsharing.processors.IAppProcessorFactory;
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
import carsharing.processors.impl.app.AppProcessorFactory;
import carsharing.processors.impl.app.CustomerProcessor;
import carsharing.processors.impl.app.ManagerProcessor;
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

        ICustomerProcessors rentNewCarProcessor = new RentNewCarProcessor(companyService, carService, customerService);
        ICustomerProcessors returnRentedCarProcessor = new ReturnRentedCarProcessor(customerService, carService);
        ICustomerProcessors showCustomerRentedCarProcessor = new ShowCustomerRentedCarProcessor(customerService, carService, companyService);
        ICustomerProcessors backFromCustomerMenuToPreviousMenuProcessor = new BackFromCustomerMenuToPreviousMenuProcessor();

        ICustomerProcessorsFactory customerProcessorsFactory = new CustomerProcessorsFactory(
                List.of(rentNewCarProcessor, returnRentedCarProcessor, showCustomerRentedCarProcessor,
                        backFromCustomerMenuToPreviousMenuProcessor));

        ICarProcessors showCarsListProcessor = new ShowCarsListProcessor(carService);
        ICarProcessors createNewCarProcessor = new CreateNewCarProcessor(carService);
        ICarProcessors backFromCarMenuToPreviousMenuProcessor = new BackFromCarMenuToPreviousMenuProcessor();

        ICarProcessorsFactory carProcessorsFactory = new CarProcessorsFactory(
                List.of(showCarsListProcessor, createNewCarProcessor, backFromCarMenuToPreviousMenuProcessor));

        ICompanyProcessors showCompaniesListProcessor = new ShowCompaniesListProcessor(companyService, carProcessorsFactory);
        ICompanyProcessors createNewCompanyProcessor = new CreateNewCompanyProcessor(companyService);
        ICompanyProcessors backFromCompanyMenuToPreviousMenuProcessor = new BackFromCompanyMenuToPreviousMenuProcessor();

        ICompanyProcessorsFactory companyProcessorsFactory = new CompanyProcessorFactory(
                List.of(showCompaniesListProcessor, createNewCompanyProcessor, backFromCompanyMenuToPreviousMenuProcessor));

        IAppActionProcessor managerProcessor = new ManagerProcessor(companyProcessorsFactory);
        IAppActionProcessor customerProcessor = new CustomerProcessor(customerService, customerProcessorsFactory);
        IAppActionProcessor createNewCustomerProcessor = new CreateNewCustomerProcessor(customerService);
        IAppActionProcessor exitApplicationProcessor = new ExitApplicationProcessor();

        IAppProcessorFactory appProcessorFactory = new AppProcessorFactory(
                List.of(managerProcessor, exitApplicationProcessor, customerProcessor, createNewCustomerProcessor));

        CarSharing carSharing = new CarSharing(appProcessorFactory);
        carSharing.runCarSharing();
    }
}