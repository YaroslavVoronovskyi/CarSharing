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

    @Mock
    private IAppProcessorsFactory appProcessorsFactoryMock;
    @Mock
    private IAppActionProcessor appActionProcessorMock;
    @InjectMocks
    private CarSharing carSharing;

    @Test
    public void shouldRunCarSharing() {
        try (MockedStatic<ConsoleReader> mockStatic = Mockito.mockStatic(ConsoleReader.class)) {
            Mockito.when(appProcessorsFactoryMock.getProcessorByAction("1")).thenReturn(appActionProcessorMock);
            mockStatic.when(() -> ConsoleReader.getStringFromConsole(Constants.APP_PROCESSOR_MESSAGE))
                    .thenReturn("1");
            carSharing.runCarSharing();
            Mockito.verify(appProcessorsFactoryMock, Mockito.times(1)).getProcessorByAction("1");
        }
    }
}
