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

    @Mock
    private ICustomerProcessor processorMock;

    @Test
    public void shouldGetCustomerProcessorByAction() {
        Mockito.when(processorMock.getSupportedCustomerActionTitle()).thenReturn("testTitle");
        ICustomerProcessorsFactory processorsFactory = new CustomerProcessorsFactory(List.of(processorMock));

        ICustomerProcessor processorFromFactory = processorsFactory.getCustomerProcessorByAction("testTitle");

        assertNotNull(processorFromFactory);
        assertEquals(processorMock, processorFromFactory);
    }

    @Test
    public void shouldReturnNullIfProcessorNotExists() {
        Mockito.when(processorMock.getSupportedCustomerActionTitle()).thenReturn("testTitle");
        ICustomerProcessorsFactory processorsFactory = new CustomerProcessorsFactory(List.of(processorMock));

        ICustomerProcessor processorFromFactory = processorsFactory.getCustomerProcessorByAction("4");

        assertNull(processorFromFactory);
    }
}
