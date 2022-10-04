package carsharing.processors.impl.car;

import carsharing.ConsoleReader;
import carsharing.Constants;
import carsharing.model.Car;
import carsharing.service.ICarService;
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
public class CreateNewCarProcessorTest {

    @Mock
    private ICarService carServiceMock;
    @InjectMocks
    private CreateNewCarProcessor createNewCarProcessor;

    @Test
    public void shouldReturnSupportedActionTitle() {
        assertEquals(createNewCarProcessor.getSupportedCarActionTitle(), "2");
    }

    @Test
    public void shouldAddNewCarProcessor() {
        try (MockedStatic<ConsoleReader> mockStatic = Mockito.mockStatic(ConsoleReader.class)) {
            Mockito.doNothing().when(carServiceMock).save(isA(Car.class));
            mockStatic.when(() -> ConsoleReader.getStringFromConsole("Enter the car name:", Constants.NAME_CHECK_PATTERN,
                    Constants.WRONG_NAME_FORMAT_ERROR)).thenReturn("BMW");
            createNewCarProcessor.doActionWithCar(1);
            Mockito.verify(carServiceMock, Mockito.times(1)).save(isA(Car.class));
            assertTrue(createNewCarProcessor.doActionWithCar(1));
        }
    }
}
