package carsharing.processors.impl.company;

import carsharing.ConsoleReader;
import carsharing.Constants;
import carsharing.model.Company;
import carsharing.processors.ICarProcessor;
import carsharing.processors.ICarProcessorsFactory;
import carsharing.service.ICompanyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ShowCompaniesListProcessorTest {

    @Mock
    private ICompanyService companyServiceMock;
    @Mock
    private ICarProcessorsFactory carProcessorsFactoryMock;
    @Mock
    private ICarProcessor carProcessorMock;
    @InjectMocks
    private ShowCompaniesListProcessor showCompaniesListProcessor;

    @Test
    public void shouldReturnSupportedActionTitle() {
        assertEquals(showCompaniesListProcessor.getSupportedCompanyActionTitle(), "1");
    }

    @Test
    public void shouldShowCompaniesList() {
        try (MockedStatic<ConsoleReader> mockStatic = Mockito.mockStatic(ConsoleReader.class)) {
            Mockito.when(companyServiceMock.getAll()).thenReturn(createFakeCompaniesList());
            Mockito.when(carProcessorsFactoryMock.getCarProcessorByAction("1")).thenReturn(carProcessorMock);
            Company company = createFakeCompany();
            mockStatic.when(() -> ConsoleReader.getIntFromConsole(3)).thenReturn(1);
            mockStatic.when(() -> ConsoleReader.getStringFromConsole(Constants.QUOTE + company.getName() +
                    Constants.QUOTE_WITH_COLON + Constants.CAR_PROCESSOR_MENU_MESSAGE)).thenReturn("1");

            showCompaniesListProcessor.doActionWithCompany();

            Mockito.verify(companyServiceMock, Mockito.times(1)).getAll();
            Mockito.verify(carProcessorsFactoryMock, Mockito.times(1)).getCarProcessorByAction("1");
        }
    }

    @Test
    public void shouldShowMessageThatCompaniesListIsEmpty() {
        Mockito.when(companyServiceMock.getAll()).thenReturn(new ArrayList<>());
        showCompaniesListProcessor.doActionWithCompany();
        Mockito.verify(companyServiceMock, Mockito.times(1)).getAll();
    }

    private Company createFakeCompany() {
        Company company = new Company("SIXT");
        company.setId(1);
        return company;
    }

    private List<Company> createFakeCompaniesList() {
        List<Company> companiesList = new ArrayList<>();

        Company firstCompany = new Company("SIXT");
        firstCompany.setId(1);
        Company secondCompany = new Company("OkCar");
        secondCompany.setId(2);
        Company thirdCompany = new Company("EuropeCar");
        thirdCompany.setId(3);

        companiesList.add(firstCompany);
        companiesList.add(secondCompany);
        companiesList.add(thirdCompany);

        return companiesList;
    }
}
