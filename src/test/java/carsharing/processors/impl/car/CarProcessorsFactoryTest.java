package carsharing.processors.impl.car;

import carsharing.processors.ICarProcessor;
import carsharing.processors.ICarProcessorsFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CarProcessorsFactoryTest {

    private final static String TEST_SUPPORTED_ACTION_TITLE = "1";
    private final static String TEST_UNSUPPORTED_ACTION_TITLE = "7";
    @Mock
    private ICarProcessor processorMock;

    @Test
    public void shouldGetCarProcessorByAction() {
        Mockito.when(processorMock.getSupportedCarActionTitle()).thenReturn(TEST_SUPPORTED_ACTION_TITLE);
        ICarProcessorsFactory processorsFactory = new CarProcessorsFactory(List.of(processorMock));

        ICarProcessor processorFromFactory = processorsFactory.getCarProcessorByAction(TEST_SUPPORTED_ACTION_TITLE);

        assertNotNull(processorFromFactory);
        assertEquals(processorMock, processorFromFactory);
    }

    @Test
    public void shouldReturnNullIfProcessorNotExists() {
        Mockito.when(processorMock.getSupportedCarActionTitle()).thenReturn(TEST_SUPPORTED_ACTION_TITLE);
        ICarProcessorsFactory processorsFactory = new CarProcessorsFactory(List.of(processorMock));

        ICarProcessor processorFromFactory = processorsFactory.getCarProcessorByAction(TEST_UNSUPPORTED_ACTION_TITLE);

        assertNull(processorFromFactory);
    }
}
