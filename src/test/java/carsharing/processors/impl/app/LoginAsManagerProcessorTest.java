package carsharing.processors.impl.app;

import carsharing.ConsoleReader;
import carsharing.Constants;
import carsharing.processors.ICompanyProcessor;
import carsharing.processors.ICompanyProcessorsFactory;
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

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class LoginAsManagerProcessorTest {

    private final static String TEST_SUPPORTED_ACTION_TITLE = "1";
    private final static String TEST_UNSUPPORTED_ACTION_TITLE = "7";
    @Mock
    private ICompanyProcessorsFactory companyProcessorsFactoryMock;
    @Mock
    private ICompanyProcessor companyProcessorMock;
    @InjectMocks
    private LoginAsManagerProcessor loginAsManagerProcessor;

    @Test
    public void shouldReturnSupportedActionTitle() {
        assertEquals(loginAsManagerProcessor.getSupportedActionTitle(), TEST_SUPPORTED_ACTION_TITLE);
    }

    @Test
    public void shouldCheckLoginAsManagerProcessor() {
        try (MockedStatic<ConsoleReader> mockStatic = Mockito.mockStatic(ConsoleReader.class)) {
            Mockito.when(companyProcessorsFactoryMock.getCompanyProcessorByAction(TEST_SUPPORTED_ACTION_TITLE))
                    .thenReturn(companyProcessorMock);
            mockStatic.when(() -> ConsoleReader.getStringFromConsole(Constants.CORRECT_TITLE_MESSAGE))
                    .thenReturn(TEST_SUPPORTED_ACTION_TITLE);
            mockStatic.when(() -> ConsoleReader.getStringFromConsole(Constants.COMPANY_PROCESSOR_MENU_MESSAGE))
                    .thenReturn(TEST_UNSUPPORTED_ACTION_TITLE);
            Mockito.when(companyProcessorMock.doActionWithCompany()).thenReturn(false);

            loginAsManagerProcessor.doAction();

            Mockito.verify(companyProcessorMock).doActionWithCompany();
            Mockito.verify(companyProcessorsFactoryMock).getCompanyProcessorByAction(TEST_UNSUPPORTED_ACTION_TITLE);
        }
    }
}
