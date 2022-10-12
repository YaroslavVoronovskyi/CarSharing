package carsharing.processors.impl.customer;

import carsharing.processors.ICustomerProcessor;
import carsharing.processors.ICustomerProcessorsFactory;
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
public class CustomerProcessorsFactoryTest {

    private final static String TEST_SUPPORTED_ACTION_TITLE = "1";
    private final static String TEST_UNSUPPORTED_ACTION_TITLE = "7";
    @Mock
    private ICustomerProcessor processorMock;

    @Test
    public void shouldGetCustomerProcessorByAction() {
        Mockito.when(processorMock.getSupportedCustomerActionTitle()).thenReturn(TEST_SUPPORTED_ACTION_TITLE);
        ICustomerProcessorsFactory processorsFactory = new CustomerProcessorsFactory(List.of(processorMock));

        ICustomerProcessor processorFromFactory = processorsFactory.getCustomerProcessorByAction(TEST_SUPPORTED_ACTION_TITLE);

        assertNotNull(processorFromFactory);
        assertEquals(processorMock, processorFromFactory);
    }

    @Test
    public void shouldReturnNullIfProcessorNotExists() {
        Mockito.when(processorMock.getSupportedCustomerActionTitle()).thenReturn(TEST_SUPPORTED_ACTION_TITLE);
        ICustomerProcessorsFactory processorsFactory = new CustomerProcessorsFactory(List.of(processorMock));

        ICustomerProcessor processorFromFactory = processorsFactory.getCustomerProcessorByAction(TEST_UNSUPPORTED_ACTION_TITLE);

        assertNull(processorFromFactory);
    }
}
