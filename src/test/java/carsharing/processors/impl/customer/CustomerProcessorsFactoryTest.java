package carsharing.processors.impl.customer;

import carsharing.dao.ICarDao;
import carsharing.dao.ICompanyDao;
import carsharing.dao.ICustomerDao;
import carsharing.dao.impl.CarDao;
import carsharing.dao.impl.CompanyDao;
import carsharing.dao.impl.CustomerDao;
import carsharing.processors.ICustomerProcessor;
import carsharing.processors.ICustomerProcessorsFactory;
import carsharing.service.ICarService;
import carsharing.service.ICompanyService;
import carsharing.service.ICustomerService;
import carsharing.service.impl.CarService;
import carsharing.service.impl.CompanyService;
import carsharing.service.impl.CustomerService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CustomerProcessorsFactoryTest {

    @Test
    public void shouldReturnSupportedActionTitle() {

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

        assertNotNull(customerProcessorsFactory.getCustomerProcessorByAction("0"));
        assertNotNull(customerProcessorsFactory.getCustomerProcessorByAction("1"));
        assertNotNull(customerProcessorsFactory.getCustomerProcessorByAction("2"));
        assertNotNull(customerProcessorsFactory.getCustomerProcessorByAction("3"));

        assertNull(customerProcessorsFactory.getCustomerProcessorByAction("4"));
        assertNull(customerProcessorsFactory.getCustomerProcessorByAction("5"));
        assertNull(customerProcessorsFactory.getCustomerProcessorByAction("6"));
    }
}
