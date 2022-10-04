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

import java.util.ArrayList;
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
    public void methodShouldReturnExpectedCar() {
        Car expectedCar = carService.getById(1);
        Car actualCar = createFakeCar();
        assertNotNull(expectedCar);
        assertEquals(expectedCar.getName(), actualCar.getName());
    }

    @Test
    public void methodShouldReturnExpectedCarsList() {
        List<Car> expectedCarsList = carService.getAllCarsByCompanyId(1);
        List<Car> actualCarsList = createFakeCarsList();
        assertNotNull(expectedCarsList);
        assertEquals(expectedCarsList.size(), 3);
        assertEquals(expectedCarsList.get(0).getId(), actualCarsList.get(0).getId());
        assertEquals(expectedCarsList.get(0).getName(), actualCarsList.get(0).getName());
    }

    @Test
    public void methodShouldSaveCar() {
        Car car = new Car("Subaru", 1);
        car.setId(4);
        carService.save(car);
        Mockito.verify(carDaoMock, Mockito.times(1)).save(car);
    }

    @Test
    public void methodShouldUpdateCar() {
        Car car = carService.getById(1);
        car.setRented(true);
        carService.update(car);
        Mockito.verify(carDaoMock, Mockito.times(1)).update(car);
    }

    private void initMocksForCar() {
        Mockito.when(carDaoMock.getById(1)).thenReturn(createFakeCar());
    }

    private void initMocksForCars() {
        Mockito.when(carDaoMock.getAllCarsByCompanyId(1)).thenReturn(createFakeCarsList());
    }

    private Car createFakeCar() {
        return new Car("BMW", 1);
    }

    private List<Car> createFakeCarsList() {
        List<Car> carsList = new ArrayList<>();

        Car firstCar = new Car("BWM", 1);
        Car secondCar = new Car("Audi", 1);
        Car thirdCar = new Car("Toyota", 1);

        carsList.add(firstCar);
        carsList.add(secondCar);
        carsList.add(thirdCar);

        return carsList;
    }
}
