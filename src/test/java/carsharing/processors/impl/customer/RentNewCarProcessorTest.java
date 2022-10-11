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
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class RentNewCarProcessorTest {

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
        assertEquals(rentNewCarProcessor.getSupportedCustomerActionTitle(), "1");
    }

    @Test
    public void shouldShowMessageThatCustomerHasAlreadyRentedCar() {
        rentNewCarProcessor.doActionWithCustomer(createTestCustomer());
        assertEquals(createTestCar().getCompanyId(), 1);
    }

    @Test
    public void shouldCheckCustomerRentNewCarProcessor() {
        try (MockedStatic<ConsoleReader> mockStatic = Mockito.mockStatic(ConsoleReader.class)) {
            Mockito.when(companyServiceMock.getAll()).thenReturn(List.of(createTestCompany()));
            Mockito.when(carServiceMock.getAllCarsByCompanyId(1)).thenReturn(List.of(createTestCar()));
            mockStatic.when(() -> ConsoleReader.getIntFromConsole(1)).thenReturn(1);
            Customer customer = createTestCustomerWithoutCar();
            rentNewCarProcessor.doActionWithCustomer(customer);

            Mockito.verify(companyServiceMock).getAll();
            Mockito.verify(carServiceMock).getAllCarsByCompanyId(1);
            Mockito.verify(customerServiceMock).update(customer);

            assertNotNull(customer.getRentedCarId());
            assertEquals(customer.getRentedCarId(), 1);
        }
    }

    @Test
    public void shouldShowMessageThatCarsListIsEmpty() {
        try (MockedStatic<ConsoleReader> mockStatic = Mockito.mockStatic(ConsoleReader.class)) {
            Mockito.when(companyServiceMock.getAll()).thenReturn(List.of(createTestCompany()));
            Mockito.when(carServiceMock.getAllCarsByCompanyId(1)).thenReturn(new ArrayList<>());
            mockStatic.when(() -> ConsoleReader.getIntFromConsole(1)).thenReturn(1);
            Customer customer = createTestCustomerWithoutCar();

            rentNewCarProcessor.doActionWithCustomer(customer);

            Mockito.verify(companyServiceMock).getAll();
            Mockito.verify(carServiceMock).getAllCarsByCompanyId(1);

            assertNotNull(customer.getRentedCarId());
            assertEquals(customer.getRentedCarId(), 0);
        }
    }

    private Customer createTestCustomer() {
        Customer customer = new Customer("Yaroslav");
        customer.setId(1);
        customer.setRentedCarId(1);
        return customer;
    }

    private Customer createTestCustomerWithoutCar() {
        Customer customer = new Customer("Yaroslav");
        customer.setId(1);
        customer.setRentedCarId(0);
        return customer;
    }

    private Car createTestCar() {
        Car car = new Car("BWM", 1);
        car.setId(1);
        return car;
    }

    private Company createTestCompany() {
        Company company = new Company("SIXT");
        company.setId(1);
        return company;
    }
}
