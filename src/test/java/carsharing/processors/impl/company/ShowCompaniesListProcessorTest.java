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

    private final static String TEST_SUPPORTED_ACTION_TITLE = "1";
    private final static String TEST_UNSUPPORTED_ACTION_TITLE = "7";
    private final static String TEST_COMPANY_NAME = "SIXT";
    private final static int TEST_COMPANY_ID = 1;
    private final static int MAX_COMPANY_NUMBER = 1;
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
        assertEquals(showCompaniesListProcessor.getSupportedCompanyActionTitle(), TEST_SUPPORTED_ACTION_TITLE);
    }

    @Test
    public void shouldCheckShowCompaniesListProcessor() {
        try (MockedStatic<ConsoleReader> mockStatic = Mockito.mockStatic(ConsoleReader.class)) {
            Mockito.when(companyServiceMock.getAll()).thenReturn(List.of(createTestCompany()));
            Mockito.when(carProcessorsFactoryMock.getCarProcessorByAction(TEST_SUPPORTED_ACTION_TITLE))
                    .thenReturn(carProcessorMock);
            Company company = createTestCompany();
            mockStatic.when(() -> ConsoleReader.getIntFromConsole(MAX_COMPANY_NUMBER)).thenReturn(TEST_COMPANY_ID);
            mockStatic.when(() -> ConsoleReader.getStringFromConsole(Constants.CORRECT_TITLE_MESSAGE))
                    .thenReturn(TEST_SUPPORTED_ACTION_TITLE);
            mockStatic.when(() -> ConsoleReader.getStringFromConsole(Constants.QUOTE + company.getName() +
                            Constants.QUOTE_WITH_COLON + Constants.CAR_PROCESSOR_MENU_MESSAGE))
                    .thenReturn(TEST_UNSUPPORTED_ACTION_TITLE);

            showCompaniesListProcessor.doActionWithCompany();

            Mockito.verify(companyServiceMock).getAll();
            Mockito.verify(carProcessorMock).doActionWithCar(TEST_COMPANY_ID);
            Mockito.verify(carProcessorsFactoryMock).getCarProcessorByAction(TEST_SUPPORTED_ACTION_TITLE);
        }
    }

    @Test
    public void shouldShowMessageThatCompaniesListIsEmpty() {
        Mockito.when(companyServiceMock.getAll()).thenReturn(new ArrayList<>());
        showCompaniesListProcessor.doActionWithCompany();
        Mockito.verify(companyServiceMock).getAll();
    }

    private Company createTestCompany() {
        Company company = new Company(TEST_COMPANY_NAME);
        company.setId(TEST_COMPANY_ID);
        return company;
    }
}
