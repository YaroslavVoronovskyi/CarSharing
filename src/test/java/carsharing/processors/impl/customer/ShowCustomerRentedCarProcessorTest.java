package carsharing.processors.impl.customer;

import carsharing.model.Car;
import carsharing.model.Company;
import carsharing.model.Customer;
import carsharing.service.ICarService;
import carsharing.service.ICompanyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ShowCustomerRentedCarProcessorTest {

    @Mock
    private ICompanyService companyServiceMock;
    @Mock
    private ICarService carServiceMock;
    @InjectMocks
    private ShowCustomerRentedCarProcessor showCustomerRentedCarProcessor;

    @Test
    public void shouldReturnSupportedActionTitle() {
        assertEquals(showCustomerRentedCarProcessor.getSupportedCustomerActionTitle(), "3");
    }

    @Test
    public void shouldCheckShowCustomerRentedCarProcessor() {
        Mockito.when(carServiceMock.getById(1)).thenReturn(createTestCar());
        Mockito.when(companyServiceMock.getById(1)).thenReturn(createTestCompany());

        showCustomerRentedCarProcessor.doActionWithCustomer(createTestCustomer());

        Mockito.verify(carServiceMock).getById(1);
        Mockito.verify(companyServiceMock).getById(1);
    }

    @Test
    public void shouldShowMassageThatCustomerDidNotRentCar() {
        Mockito.when(carServiceMock.getById(1)).thenReturn(createTestCar());
        Mockito.when(companyServiceMock.getById(1)).thenReturn(createTestCompany());

        showCustomerRentedCarProcessor.doActionWithCustomer(createTestCustomerWithoutCar());

        Mockito.verify(carServiceMock, Mockito.times(0)).getById(1);
        Mockito.verify(companyServiceMock, Mockito.times(0)).getById(1);
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
