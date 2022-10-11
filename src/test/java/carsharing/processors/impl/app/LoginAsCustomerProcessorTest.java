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
        assertEquals(loginAsCustomerProcessor.getSupportedActionTitle(), "2");
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
            Mockito.when(customerProcessorsFactoryMock.getCustomerProcessorByAction("2"))
                    .thenReturn(carProcessorMock);
            mockStatic.when(() -> ConsoleReader.getIntFromConsole(1)).thenReturn(1);
            mockStatic.when(() -> ConsoleReader.getStringFromConsole(Constants.CORRECT_TITLE_MESSAGE)).thenReturn("2");
            mockStatic.when(() -> ConsoleReader.getStringFromConsole(Constants.QUOTE + createTestCustomer().getName()
                            + Constants.QUOTE_WITH_COLON + Constants.CUSTOMER_PROCESSOR_MENU_MESSAGE))
                    .thenReturn("7");
            Mockito.when(carProcessorMock.doActionWithCustomer(createTestCustomer())).thenReturn(false);

            loginAsCustomerProcessor.doAction();

            Mockito.verify(customerServiceMock).getAll();
            Mockito.verify(customerProcessorsFactoryMock).getCustomerProcessorByAction("2");
            Mockito.verify(carProcessorMock).doActionWithCustomer(createTestCustomer());
        }
    }

    private Customer createTestCustomer() {
        Customer customer = new Customer("Yaroslav");
        customer.setId(1);
        customer.setRentedCarId(0);
        return customer;
    }
}
