package carsharing.processors.impl.company;

import carsharing.dao.ICompanyDao;
import carsharing.dao.impl.CompanyDao;
import carsharing.processors.ICarProcessorsFactory;
import carsharing.processors.ICompanyProcessor;
import carsharing.processors.ICompanyProcessorsFactory;
import carsharing.processors.impl.car.CarProcessorsFactory;
import carsharing.service.ICompanyService;
import carsharing.service.impl.CompanyService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CompanyProcessorFactoryTest {

    @Test
    public void shouldReturnSupportedActionTitle() {
        ICompanyDao companyDao = new CompanyDao();
        ICompanyService companyService = new CompanyService(companyDao);

        ICarProcessorsFactory carProcessorsFactory = new CarProcessorsFactory(List.of());

        ICompanyProcessor showCompaniesListProcessor = new ShowCompaniesListProcessor(companyService, carProcessorsFactory);
        ICompanyProcessor createNewCompanyProcessor = new CreateNewCompanyProcessor(companyService);
        ICompanyProcessor backFromCompanyMenuToPreviousMenuProcessor = new BackFromCompanyMenuToPreviousMenuProcessor();

        ICompanyProcessorsFactory companyProcessorsFactory = new CompanyProcessorFactory(
                List.of(showCompaniesListProcessor, createNewCompanyProcessor, backFromCompanyMenuToPreviousMenuProcessor));

        assertNotNull(companyProcessorsFactory.getCompanyProcessorByAction("0"));
        assertNotNull(companyProcessorsFactory.getCompanyProcessorByAction("1"));
        assertNotNull(companyProcessorsFactory.getCompanyProcessorByAction("2"));

        assertNull(companyProcessorsFactory.getCompanyProcessorByAction("3"));
        assertNull(companyProcessorsFactory.getCompanyProcessorByAction("4"));
        assertNull(companyProcessorsFactory.getCompanyProcessorByAction("5"));
        assertNull(companyProcessorsFactory.getCompanyProcessorByAction("6"));
    }
}
