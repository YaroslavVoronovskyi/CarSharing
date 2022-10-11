package carsharing.processors.impl.app;

import carsharing.processors.IAppActionProcessor;
import carsharing.processors.IAppProcessorsFactory;
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
public class AppProcessorsFactoryTest {

    @Mock
    private IAppActionProcessor processorMock;

    @Test
    public void shouldCheckGetProcessorByActionProcessor() {
        Mockito.when(processorMock.getSupportedActionTitle()).thenReturn("testTitle");
        IAppProcessorsFactory processorsFactory = new AppProcessorsFactory(List.of(processorMock));

        IAppActionProcessor processorFromFactory = processorsFactory.getProcessorByAction("testTitle");

        assertNotNull(processorFromFactory);
        assertEquals(processorMock, processorFromFactory);
    }

    @Test
    public void shouldReturnNullIfProcessorNotExists() {
        Mockito.when(processorMock.getSupportedActionTitle()).thenReturn("testTitle");
        IAppProcessorsFactory processorsFactory = new AppProcessorsFactory(List.of(processorMock));

        IAppActionProcessor processorFromFactory = processorsFactory.getProcessorByAction("4");

        assertNull(processorFromFactory);
    }
}
