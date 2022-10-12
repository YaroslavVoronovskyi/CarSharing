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

    private final static String TEST_SUPPORTED_ACTION_TITLE = "3";
    private final static String TEST_CUSTOMER_NAME = "Yaroslav";
    private final static int TEST_CUSTOMER_ID = 1;
    private final static String TEST_CAR_NAME = "BMW";
    private final static int TEST_CAR_ID = 1;
    private final static String TEST_COMPANY_NAME = "SIXT";
    private final static int TEST_COMPANY_ID = 1;
    @Mock
    private ICompanyService companyServiceMock;
    @Mock
    private ICarService carServiceMock;
    @InjectMocks
    private ShowCustomerRentedCarProcessor showCustomerRentedCarProcessor;

    @Test
    public void shouldReturnSupportedActionTitle() {
        assertEquals(showCustomerRentedCarProcessor.getSupportedCustomerActionTitle(), TEST_SUPPORTED_ACTION_TITLE);
    }

    @Test
    public void shouldCheckShowCustomerRentedCarProcessor() {
        Mockito.when(carServiceMock.getById(TEST_CAR_ID)).thenReturn(createTestCar());
        Mockito.when(companyServiceMock.getById(TEST_COMPANY_ID)).thenReturn(createTestCompany());

        showCustomerRentedCarProcessor.doActionWithCustomer(createTestCustomer());

        Mockito.verify(carServiceMock).getById(TEST_CAR_ID);
        Mockito.verify(companyServiceMock).getById(TEST_COMPANY_ID);
    }

    @Test
    public void shouldShowMassageThatCustomerDidNotRentCar() {
        Mockito.when(carServiceMock.getById(TEST_CAR_ID)).thenReturn(createTestCar());
        Mockito.when(companyServiceMock.getById(TEST_COMPANY_ID)).thenReturn(createTestCompany());

        showCustomerRentedCarProcessor.doActionWithCustomer(createTestCustomerWithoutCar());

        Mockito.verify(carServiceMock, Mockito.times(0)).getById(TEST_CAR_ID);
        Mockito.verify(companyServiceMock, Mockito.times(0)).getById(TEST_COMPANY_ID);
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
