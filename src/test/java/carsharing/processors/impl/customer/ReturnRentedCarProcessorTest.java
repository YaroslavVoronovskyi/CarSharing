package carsharing.processors.impl.customer;

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
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ReturnRentedCarProcessorTest {

    @Mock
    private ICompanyService companyServiceMock;
    @Mock
    private ICarService carServiceMock;
    @Mock
    private ICustomerService customerService;
    @InjectMocks
    private ReturnRentedCarProcessor returnRentedCarProcessor;

    @Test
    public void shouldReturnSupportedActionTitle() {
        assertEquals(returnRentedCarProcessor.getSupportedCustomerActionTitle(), "2");
    }

    @Test
    public void shouldReturnRentedCar() {
        Customer customer = createFakeCustomer();
        returnRentedCarProcessor.doActionWithCustomer(customer);
        Mockito.verify(customerService, Mockito.times(1)).update(customer);
        assertNull(customer.getRentedCarId());
    }

    @Test
    public void shouldShowMassageThatCustomerDidNotRentCar() {
        Mockito.when(carServiceMock.getById(1)).thenReturn(createFakeCar());
        Mockito.when(companyServiceMock.getById(1)).thenReturn(createFakeCompany());
        returnRentedCarProcessor.doActionWithCustomer(createFakeCustomerWithoutCar());
        Mockito.verify(carServiceMock, Mockito.times(0)).getById(1);
        Mockito.verify(companyServiceMock, Mockito.times(0)).getById(1);
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

    private Company createFakeCompany() {
        Company company = new Company("SIXT");
        company.setId(1);
        return company;
    }
}
