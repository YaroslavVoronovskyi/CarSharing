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

    private final static String TEST_SUPPORTED_ACTION_TITLE = "2";
    private final static String TEST_COMPANY_NAME = "SIXT";
    @Mock
    private ICompanyService companyServiceMock;
    @InjectMocks
    private CreateNewCompanyProcessor createNewCompanyProcessor;

    @Test
    public void shouldReturnSupportedActionTitle() {
        assertEquals(createNewCompanyProcessor.getSupportedCompanyActionTitle(), TEST_SUPPORTED_ACTION_TITLE);
    }

    @Test
    public void shouldCheckAddNewCompanyProcessor() {
        try (MockedStatic<ConsoleReader> mockStatic = Mockito.mockStatic(ConsoleReader.class)) {
            Mockito.doNothing().when(companyServiceMock).save(isA(Company.class));
            mockStatic.when(() -> ConsoleReader.getStringFromConsole("Enter the company name:",
                    Constants.NAME_CHECK_PATTERN, Constants.WRONG_NAME_FORMAT_ERROR)).thenReturn(TEST_COMPANY_NAME);

            createNewCompanyProcessor.doActionWithCompany();

            Mockito.verify(companyServiceMock).save(isA(Company.class));
            assertTrue(createNewCompanyProcessor.doActionWithCompany());
        }
    }
}
