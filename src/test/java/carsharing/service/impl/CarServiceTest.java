package carsharing.service.impl;

import carsharing.dao.ICarDao;
import carsharing.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CarServiceTest {

    private final static String TEST_CAR_NAME = "BMW";
    private final static int TEST_CAR_ID = 1;
    private final static int TEST_COMPANY_ID = 1;
    @Mock
    ICarDao carDaoMock;
    @InjectMocks
    CarService carService;

    @BeforeEach
    public void init() {
        initMocksForCars();
        initMocksForCar();
    }

    @Test
    public void shouldReturnExpectedCar() {
        Car expectedCar = carService.getById(TEST_CAR_ID);
        Car actualCar = createTestCar();
        assertEquals(expectedCar, actualCar);
    }

    @Test
    public void shouldReturnExpectedCarsList() {
        List<Car> expectedCarsList = carService.getAllCarsByCompanyId(TEST_COMPANY_ID);
        List<Car> actualCarsList = List.of(createTestCar());
        assertEquals(expectedCarsList, actualCarsList);
    }

    @Test
    public void shouldSaveCar() {
        Car car = new Car(TEST_CAR_NAME, TEST_COMPANY_ID);
        car.setId(TEST_CAR_ID);
        carService.save(car);
        Mockito.verify(carDaoMock).save(car);
    }

    @Test
    public void shouldUpdateCar() {
        Car car = carService.getById(TEST_CAR_ID);
        car.setRented(true);
        carService.update(car);
        Mockito.verify(carDaoMock).update(car);
    }

    private void initMocksForCar() {
        Mockito.when(carDaoMock.getById(TEST_CAR_ID)).thenReturn(createTestCar());
    }

    private void initMocksForCars() {
        Mockito.when(carDaoMock.getAllCarsByCompanyId(TEST_COMPANY_ID)).thenReturn(List.of(createTestCar()));
    }

    private Car createTestCar() {
        return new Car(TEST_CAR_NAME, TEST_COMPANY_ID);
    }
}