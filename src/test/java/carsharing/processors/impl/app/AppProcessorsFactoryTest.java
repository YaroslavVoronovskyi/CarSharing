package carsharing.processors.impl.app;

import carsharing.dao.ICustomerDao;
import carsharing.dao.impl.CustomerDao;
import carsharing.processors.IAppActionProcessor;
import carsharing.processors.IAppProcessorsFactory;
import carsharing.processors.ICompanyProcessorsFactory;
import carsharing.processors.ICustomerProcessorsFactory;
import carsharing.processors.impl.company.CompanyProcessorFactory;
import carsharing.processors.impl.customer.CustomerProcessorsFactory;
import carsharing.service.ICustomerService;
import carsharing.service.impl.CustomerService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AppProcessorsFactoryTest {

    @Test
    public void shouldReturnSupportedActionTitle() {
        ICompanyProcessorsFactory companyProcessorsFactory = new CompanyProcessorFactory(List.of());
        IAppActionProcessor loginAsManagerProcessor = new LoginAsManagerProcessor(companyProcessorsFactory);
        ICustomerDao customerDao = new CustomerDao();
        ICustomerService customerService = new CustomerService(customerDao);
        ICustomerProcessorsFactory customerProcessorsFactory = new CustomerProcessorsFactory(List.of());
        IAppActionProcessor loginAsCustomerProcessor = new LoginAsCustomerProcessor(customerService, customerProcessorsFactory);
        IAppActionProcessor createNewCustomerProcessor = new CreateNewCustomerProcessor(customerService);
        IAppActionProcessor exitApplicationProcessor = new ExitApplicationProcessor();
        IAppProcessorsFactory processorsFactory = new AppProcessorsFactory(List.of(loginAsManagerProcessor, exitApplicationProcessor,
                loginAsCustomerProcessor, createNewCustomerProcessor));

        assertNotNull(processorsFactory.getProcessorByAction("0"));
        assertNotNull(processorsFactory.getProcessorByAction("1"));
        assertNotNull(processorsFactory.getProcessorByAction("2"));
        assertNotNull(processorsFactory.getProcessorByAction("3"));

        assertNull(processorsFactory.getProcessorByAction("4"));
        assertNull(processorsFactory.getProcessorByAction("5"));
        assertNull(processorsFactory.getProcessorByAction("6"));
    }
}
