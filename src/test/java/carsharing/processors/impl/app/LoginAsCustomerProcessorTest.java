package carsharing.processors.impl.app;

import carsharing.ConsoleReader;
import carsharing.Constants;
import carsharing.model.Customer;
import carsharing.processors.ICustomerProcessor;
import carsharing.processors.ICustomerProcessorsFactory;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class LoginAsCustomerProcessorTest {

    private final static String TEST_SUPPORTED_ACTION_TITLE = "2";
    private final static String TEST_UNSUPPORTED_ACTION_TITLE = "7";
    private final static String TEST_CUSTOMER_NAME = "Yaroslav";
    private final static int TEST_CUSTOMER_ID = 1;
    private final static int MAX_CUSTOMER_NUMBER = 1;

    @Mock
    private ICustomerService customerServiceMock;
    @Mock
    private ICustomerProcessorsFactory customerProcessorsFactoryMock;
    @Mock
    private ICustomerProcessor carProcessorMock;
    @InjectMocks
    private LoginAsCustomerProcessor loginAsCustomerProcessor;

    @Test
    public void shouldReturnSupportedActionTitle() {
        assertEquals(loginAsCustomerProcessor.getSupportedActionTitle(), TEST_SUPPORTED_ACTION_TITLE);
    }

    @Test
    public void shouldShowMessageThatCustomersListIsEmpty() {
        Mockito.when(customerServiceMock.getAll()).thenReturn(new ArrayList<>());
        loginAsCustomerProcessor.doAction();
        Mockito.verify(customerServiceMock).getAll();
    }

    @Test
    public void shouldCheckLoginAsCustomerProcessor() {
        try (MockedStatic<ConsoleReader> mockStatic = Mockito.mockStatic(ConsoleReader.class)) {
            Mockito.when(customerServiceMock.getAll()).thenReturn(List.of(createTestCustomer()));
            Mockito.when(customerProcessorsFactoryMock.getCustomerProcessorByAction(TEST_SUPPORTED_ACTION_TITLE))
                    .thenReturn(carProcessorMock);
            mockStatic.when(() -> ConsoleReader.getIntFromConsole(MAX_CUSTOMER_NUMBER)).thenReturn(TEST_CUSTOMER_ID);
            mockStatic.when(() -> ConsoleReader.getStringFromConsole(Constants.CORRECT_TITLE_MESSAGE))
                    .thenReturn(TEST_SUPPORTED_ACTION_TITLE);
            mockStatic.when(() -> ConsoleReader.getStringFromConsole(Constants.QUOTE + createTestCustomer().getName()
                            + Constants.QUOTE_WITH_COLON + Constants.CUSTOMER_PROCESSOR_MENU_MESSAGE))
                    .thenReturn(TEST_UNSUPPORTED_ACTION_TITLE);
            Mockito.when(carProcessorMock.doActionWithCustomer(createTestCustomer())).thenReturn(false);

            loginAsCustomerProcessor.doAction();

            Mockito.verify(customerServiceMock).getAll();
            Mockito.verify(customerProcessorsFactoryMock).getCustomerProcessorByAction(TEST_SUPPORTED_ACTION_TITLE);
            Mockito.verify(carProcessorMock).doActionWithCustomer(createTestCustomer());
        }
    }

    private Customer createTestCustomer() {
        Customer customer = new Customer(TEST_CUSTOMER_NAME);
        customer.setId(TEST_CUSTOMER_ID);
        customer.setRentedCarId(null);
        return customer;
    }
}
