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

    @Mock
    private ICompanyProcessorsFactory companyProcessorsFactoryMock;
    @Mock
    private ICompanyProcessor companyProcessorMock;
    @InjectMocks
    private LoginAsManagerProcessor loginAsManagerProcessor;

    @Test
    public void shouldReturnSupportedActionTitle() {
        assertEquals(loginAsManagerProcessor.getSupportedActionTitle(), "1");
    }

    @Test
    public void shouldLoginAsManager() {
        try (MockedStatic<ConsoleReader> mockStatic = Mockito.mockStatic(ConsoleReader.class)) {
            Mockito.when(companyProcessorsFactoryMock.getCompanyProcessorByAction("1")).thenReturn(companyProcessorMock);
            mockStatic.when(() -> ConsoleReader.getStringFromConsole(Constants.COMPANY_PROCESSOR_MENU_MESSAGE))
                    .thenReturn("1");
            loginAsManagerProcessor.doAction();
            Mockito.verify(companyProcessorsFactoryMock, Mockito.times(1)).getCompanyProcessorByAction("1");
        }
    }
}
