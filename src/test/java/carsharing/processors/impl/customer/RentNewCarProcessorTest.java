package carsharing.processors.impl.customer;

import carsharing.ConsoleReader;
import carsharing.model.Car;
import carsharing.model.Company;
import carsharing.model.Customer;
import carsharing.service.ICarService;
import carsharing.service.ICompanyService;
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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class RentNewCarProcessorTest {

    private final static String TEST_SUPPORTED_ACTION_TITLE = "1";
    private final static String TEST_CUSTOMER_NAME = "Yaroslav";
    private final static int TEST_CUSTOMER_ID = 1;
    private final static String TEST_CAR_NAME = "BMW";
    private final static int TEST_CAR_ID = 1;
    private final static String TEST_COMPANY_NAME = "SIXT";
    private final static int TEST_COMPANY_ID = 1;
    private final static int MAX_CAR_NUMBER = 1;
    @Mock
    private ICompanyService companyServiceMock;
    @Mock
    private ICarService carServiceMock;
    @Mock
    private ICustomerService customerServiceMock;
    @InjectMocks
    private RentNewCarProcessor rentNewCarProcessor;

    @Test
    public void shouldReturnSupportedActionTitle() {
        assertEquals(rentNewCarProcessor.getSupportedCustomerActionTitle(), TEST_SUPPORTED_ACTION_TITLE);
    }

    @Test
    public void shouldShowMessageThatCustomerHasAlreadyRentedCar() {
        rentNewCarProcessor.doActionWithCustomer(createTestCustomer());
        assertEquals(createTestCar().getCompanyId(), TEST_COMPANY_ID);
    }

    @Test
    public void shouldCheckCustomerRentNewCarProcessor() {
        try (MockedStatic<ConsoleReader> mockStatic = Mockito.mockStatic(ConsoleReader.class)) {
            Mockito.when(companyServiceMock.getAll()).thenReturn(List.of(createTestCompany()));
            Mockito.when(carServiceMock.getAllCarsByCompanyId(TEST_COMPANY_ID)).thenReturn(List.of(createTestCar()));
            mockStatic.when(() -> ConsoleReader.getIntFromConsole(MAX_CAR_NUMBER)).thenReturn(TEST_CAR_ID);
            Customer customer = createTestCustomerWithoutCar();
            rentNewCarProcessor.doActionWithCustomer(customer);

            Mockito.verify(companyServiceMock).getAll();
            Mockito.verify(carServiceMock).getAllCarsByCompanyId(TEST_COMPANY_ID);
            Mockito.verify(customerServiceMock).update(customer);
            assertNotNull(customer.getRentedCarId());
            assertEquals(customer.getRentedCarId(), TEST_CAR_ID);
        }
    }

    @Test
    public void shouldShowMessageThatCarsListIsEmpty() {
        try (MockedStatic<ConsoleReader> mockStatic = Mockito.mockStatic(ConsoleReader.class)) {
            Mockito.when(companyServiceMock.getAll()).thenReturn(List.of(createTestCompany()));
            Mockito.when(carServiceMock.getAllCarsByCompanyId(TEST_COMPANY_ID)).thenReturn(new ArrayList<>());
            mockStatic.when(() -> ConsoleReader.getIntFromConsole(MAX_CAR_NUMBER)).thenReturn(TEST_CAR_ID);
            Customer customer = createTestCustomerWithoutCar();

            rentNewCarProcessor.doActionWithCustomer(customer);

            Mockito.verify(companyServiceMock).getAll();
            Mockito.verify(carServiceMock).getAllCarsByCompanyId(TEST_COMPANY_ID);
            assertNull(customer.getRentedCarId());
        }
    }

    private Customer createTestCustomer() {
        Customer customer = new Customer(TEST_CUSTOMER_NAME);
        customer.setId(TEST_CUSTOMER_ID);
        customer.setRentedCarId(TEST_CAR_ID);
        return customer;
    }

    private Customer createTestCustomerWithoutCar() {
        Customer customer = new Customer(TEST_CUSTOMER_NAME);
        customer.setId(TEST_CUSTOMER_ID);
        customer.setRentedCarId(null);
        return customer;
    }

    private Car createTestCar() {
        Car car = new Car(TEST_CAR_NAME, TEST_COMPANY_ID);
        car.setId(TEST_CAR_ID);
        return car;
    }

    private Company createTestCompany() {
        Company company = new Company(TEST_COMPANY_NAME);
        company.setId(TEST_COMPANY_ID);
        return company;
    }
}
