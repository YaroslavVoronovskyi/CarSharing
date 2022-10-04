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
        rentNewCarProcessor.doActionWithCustomer(createFakeCustomer());
        assertEquals(createFakeCar().getCompanyId(), 1);
    }

    @Test
    public void shouldCustomerRentNewCar() {
        try (MockedStatic<ConsoleReader> mockStatic = Mockito.mockStatic(ConsoleReader.class)) {
            Mockito.when(companyServiceMock.getAll()).thenReturn(createFakeCompaniesList());
            Mockito.when(carServiceMock.getAllCarsByCompanyId(1)).thenReturn(createFakeCarsList());
            mockStatic.when(() -> ConsoleReader.getIntFromConsole(3)).thenReturn(1);
            Customer customer = createFakeCustomerWithoutCar();
            rentNewCarProcessor.doActionWithCustomer(customer);

            Mockito.verify(companyServiceMock, Mockito.times(1)).getAll();
            Mockito.verify(carServiceMock, Mockito.times(1)).getAllCarsByCompanyId(1);
            Mockito.verify(customerServiceMock, Mockito.times(1)).update(customer);

            assertNotNull(customer.getRentedCarId());
            assertEquals(customer.getRentedCarId(), 1);
        }
    }

    @Test
    public void shouldShowMessageThatCarsListIsEmpty() {
        try (MockedStatic<ConsoleReader> mockStatic = Mockito.mockStatic(ConsoleReader.class)) {
            Mockito.when(companyServiceMock.getAll()).thenReturn(createFakeCompaniesList());
            Mockito.when(carServiceMock.getAllCarsByCompanyId(1)).thenReturn(new ArrayList<>());
            mockStatic.when(() -> ConsoleReader.getIntFromConsole(3)).thenReturn(1);
            Customer customer = createFakeCustomerWithoutCar();
            rentNewCarProcessor.doActionWithCustomer(customer);

            Mockito.verify(companyServiceMock, Mockito.times(1)).getAll();
            Mockito.verify(carServiceMock, Mockito.times(1)).getAllCarsByCompanyId(1);

            assertNotNull(customer.getRentedCarId());
            assertEquals(customer.getRentedCarId(), 0);
        }
    }

    private Customer createFakeCustomer() {
        Customer customer = new Customer("Yaroslav");
        customer.setId(1);
        customer.setRentedCarId(1);
        return customer;
    }

    private Customer createFakeCustomerWithoutCar() {
        Customer customer = new Customer("Yaroslav");
        customer.setId(1);
        customer.setRentedCarId(0);
        return customer;
    }

    private Car createFakeCar() {
        Car car = new Car("BWM", 1);
        car.setId(1);
        return car;
    }

    private List<Car> createFakeCarsList() {
        List<Car> carsList = new ArrayList<>();

        Car firstCar = new Car("BWM", 1);
        firstCar.setId(1);
        Car secondCar = new Car("Audi", 1);
        secondCar.setId(2);
        Car thirdCar = new Car("Toyota", 1);
        thirdCar.setId(3);

        carsList.add(firstCar);
        carsList.add(secondCar);
        carsList.add(thirdCar);

        return carsList;
    }

    private List<Company> createFakeCompaniesList() {
        List<Company> companiesList = new ArrayList<>();

        Company firstCompany = new Company("SIXT");
        firstCompany.setId(1);
        Company secondCompany = new Company("OkCar");
        secondCompany.setId(2);
        Company thirdCompany = new Company("EuropeCar");
        thirdCompany.setId(3);

        companiesList.add(firstCompany);
        companiesList.add(secondCompany);
        companiesList.add(thirdCompany);

        return companiesList;
    }
}
