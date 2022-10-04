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
        Mockito.verify(customerServiceMock, Mockito.times(1)).getAll();
    }

    @Test
    public void shouldLoginAsCustomer() {
        try (MockedStatic<ConsoleReader> mockStatic = Mockito.mockStatic(ConsoleReader.class)) {
            Mockito.when(customerServiceMock.getAll()).thenReturn(createFakeCustomersList());
            Mockito.when(customerProcessorsFactoryMock.getCustomerProcessorByAction("2")).thenReturn(carProcessorMock);
            Customer customer = createFakeCustomer();
            mockStatic.when(() -> ConsoleReader.getIntFromConsole(3)).thenReturn(1);
            mockStatic.when(() -> ConsoleReader.getStringFromConsole(Constants.QUOTE + customer.getName()
                            + Constants.QUOTE_WITH_COLON + Constants.CUSTOMER_PROCESSOR_MENU_MESSAGE))
                    .thenReturn("2");

            loginAsCustomerProcessor.doAction();

            Mockito.verify(customerServiceMock, Mockito.times(1)).getAll();
            Mockito.verify(customerProcessorsFactoryMock, Mockito.times(1)).getCustomerProcessorByAction("2");
        }
    }

    private Customer createFakeCustomer() {
        Customer customer = new Customer("Yaroslav");
        customer.setId(1);
        customer.setRentedCarId(0);
        return customer;
    }

    private List<Customer> createFakeCustomersList() {
        List<Customer> customersList = new ArrayList<>();

        Customer firstCustomer = new Customer("Yaroslav");
        firstCustomer.setId(1);
        firstCustomer.setRentedCarId(0);
        Customer secondCustomer = new Customer("Piter");
        secondCustomer.setId(2);
        secondCustomer.setRentedCarId(0);
        Customer thirdCustomer = new Customer("Boris");
        thirdCustomer.setId(3);
        thirdCustomer.setRentedCarId(0);

        customersList.add(firstCustomer);
        customersList.add(secondCustomer);
        customersList.add(thirdCustomer);

        return customersList;
    }
}
