package carsharing.processors.impl.app;

import carsharing.ConsoleReader;
import carsharing.Constants;
import carsharing.model.Customer;
import carsharing.service.ICustomerService;
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
public class CreateNewCustomerProcessorTest {

    private final static String TEST_SUPPORTED_ACTION_TITLE = "3";
    private final static String TEST_CUSTOMER_NAME = "Yaroslav";
    @Mock
    private ICustomerService customerServiceMock;
    @InjectMocks
    private CreateNewCustomerProcessor createNewCustomerProcessor;

    @Test
    public void shouldReturnSupportedActionTitle() {
        assertEquals(createNewCustomerProcessor.getSupportedActionTitle(), TEST_SUPPORTED_ACTION_TITLE);
    }

    @Test
    public void shouldCheckAddNewCustomerProcessor() {
        try (MockedStatic<ConsoleReader> mockStatic = Mockito.mockStatic(ConsoleReader.class)) {
            Mockito.doNothing().when(customerServiceMock).save(isA(Customer.class));
            mockStatic.when(() -> ConsoleReader.getStringFromConsole("Enter the customer name:",
                    Constants.NAME_CHECK_PATTERN, Constants.WRONG_NAME_FORMAT_ERROR)).thenReturn(TEST_CUSTOMER_NAME);

            createNewCustomerProcessor.doAction();

            Mockito.verify(customerServiceMock).save(isA(Customer.class));
            assertTrue(createNewCustomerProcessor.doAction());
        }
    }
}
