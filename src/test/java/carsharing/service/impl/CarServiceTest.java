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
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CarServiceTest {

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
        Car expectedCar = carService.getById(1);
        Car actualCar = createTestCar();
        assertNotNull(expectedCar);
        assertEquals(expectedCar.getName(), actualCar.getName());
    }

    @Test
    public void shouldReturnExpectedCarsList() {
        List<Car> expectedCarsList = carService.getAllCarsByCompanyId(1);
        List<Car> actualCarsList = List.of(createTestCar());
        assertNotNull(expectedCarsList);
        assertEquals(expectedCarsList.size(), 1);
        assertEquals(expectedCarsList.get(0).getId(), actualCarsList.get(0).getId());
        assertEquals(expectedCarsList.get(0).getName(), actualCarsList.get(0).getName());
    }

    @Test
    public void shouldSaveCar() {
        Car car = new Car("Subaru", 1);
        car.setId(4);
        carService.save(car);
        Mockito.verify(carDaoMock).save(car);
    }

    @Test
    public void shouldUpdateCar() {
        Car car = carService.getById(1);
        car.setRented(true);
        carService.update(car);
        Mockito.verify(carDaoMock).update(car);
    }

    private void initMocksForCar() {
        Mockito.when(carDaoMock.getById(1)).thenReturn(createTestCar());
    }

    private void initMocksForCars() {
        Mockito.when(carDaoMock.getAllCarsByCompanyId(1)).thenReturn(List.of(createTestCar()));
    }

    private Car createTestCar() {
        return new Car("BMW", 1);
    }
}