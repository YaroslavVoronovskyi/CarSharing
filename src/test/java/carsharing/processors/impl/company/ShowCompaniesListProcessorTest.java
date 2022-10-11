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
    public void shouldCheckShowCompaniesListProcessor() {
        try (MockedStatic<ConsoleReader> mockStatic = Mockito.mockStatic(ConsoleReader.class)) {
            Mockito.when(companyServiceMock.getAll()).thenReturn(List.of(createTestCompany()));
            Mockito.when(carProcessorsFactoryMock.getCarProcessorByAction("1")).thenReturn(carProcessorMock);
            Company company = createTestCompany();
            mockStatic.when(() -> ConsoleReader.getIntFromConsole(1)).thenReturn(1);
            mockStatic.when(() -> ConsoleReader.getStringFromConsole(Constants.CORRECT_TITLE_MESSAGE)).thenReturn("1");
            mockStatic.when(() -> ConsoleReader.getStringFromConsole(Constants.QUOTE + company.getName() +
                    Constants.QUOTE_WITH_COLON + Constants.CAR_PROCESSOR_MENU_MESSAGE)).thenReturn("7");

            showCompaniesListProcessor.doActionWithCompany();

            Mockito.verify(companyServiceMock).getAll();
            Mockito.verify(carProcessorMock).doActionWithCar(1);
            Mockito.verify(carProcessorsFactoryMock).getCarProcessorByAction("1");
        }
    }

    @Test
    public void shouldShowMessageThatCompaniesListIsEmpty() {
        Mockito.when(companyServiceMock.getAll()).thenReturn(new ArrayList<>());
        showCompaniesListProcessor.doActionWithCompany();
        Mockito.verify(companyServiceMock).getAll();
    }

    private Company createTestCompany() {
        Company company = new Company("SIXT");
        company.setId(1);
        return company;
    }
}
