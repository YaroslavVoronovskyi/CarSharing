package carsharing;

import carsharing.processors.IAppActionProcessor;
import carsharing.processors.IAppProcessorsFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CarSharingTest {

    private final static String TEST_SUPPORTED_ACTION_TITLE = "1";
    private final static String TEST_UNSUPPORTED_ACTION_TITLE = "7";
    @Mock
    private IAppProcessorsFactory appProcessorsFactoryMock;
    @Mock
    private IAppActionProcessor appActionProcessorMock;
    @InjectMocks
    private CarSharing carSharing;

    @Test
    public void shouldRunCarSharing() {
        try (MockedStatic<ConsoleReader> mockStatic = Mockito.mockStatic(ConsoleReader.class)) {
            Mockito.when(appProcessorsFactoryMock.getProcessorByAction(TEST_SUPPORTED_ACTION_TITLE))
                    .thenReturn(appActionProcessorMock);
            Mockito.when(appActionProcessorMock.doAction()).thenReturn(false);
            mockStatic.when(() -> ConsoleReader.getStringFromConsole(Constants.CORRECT_TITLE_MESSAGE))
                    .thenReturn(TEST_SUPPORTED_ACTION_TITLE);
            mockStatic.when(() -> ConsoleReader.getStringFromConsole(Constants.APP_PROCESSOR_MESSAGE))
                    .thenReturn(TEST_UNSUPPORTED_ACTION_TITLE);

            carSharing.runCarSharing();

            Mockito.verify(appProcessorsFactoryMock).getProcessorByAction(TEST_SUPPORTED_ACTION_TITLE);
            Mockito.verify(appActionProcessorMock).doAction();
        }
    }
}
