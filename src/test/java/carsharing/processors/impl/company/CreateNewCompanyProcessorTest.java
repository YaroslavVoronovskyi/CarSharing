package carsharing.processors.impl.company;

import carsharing.ConsoleReader;
import carsharing.Constants;
import carsharing.model.Company;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CreateNewCompanyProcessorTest {

    @Mock
    private ICompanyService companyServiceMock;
    @InjectMocks
    private CreateNewCompanyProcessor createNewCompanyProcessor;

    @Test
    public void shouldReturnSupportedActionTitle() {
        assertEquals(createNewCompanyProcessor.getSupportedCompanyActionTitle(), "2");
    }

    @Test
    public void shouldAddNewCompanyProcessor() {
        try (MockedStatic<ConsoleReader> mockStatic = Mockito.mockStatic(ConsoleReader.class)) {
            Mockito.doNothing().when(companyServiceMock).save(isA(Company.class));
            mockStatic.when(() -> ConsoleReader.getStringFromConsole("Enter the company name:", Constants.NAME_CHECK_PATTERN,
                    Constants.WRONG_NAME_FORMAT_ERROR)).thenReturn("SIXT");
            createNewCompanyProcessor.doActionWithCompany();
            Mockito.verify(companyServiceMock, Mockito.times(1)).save(isA(Company.class));
            assertTrue(createNewCompanyProcessor.doActionWithCompany());
        }
    }
}
