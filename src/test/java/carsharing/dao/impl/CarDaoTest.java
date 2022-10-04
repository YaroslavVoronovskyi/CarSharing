package carsharing.dao.impl;

import carsharing.dao.ICarDao;
import carsharing.model.Car;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CarDaoTest extends AbstractDaoTest {

    private final ICarDao carDao = new CarDao();

    @Test()
    public void shouldReturnExpectedNumberOfCars() {
        List<Car> carsList = carDao.getAllCarsByCompanyId(1);
        assertNotNull(carsList);
        assertEquals(carsList.size(), 1);
    }

    @Test()
    public void shouldCreateNewCarAndReturnExpectedNumberOfCars() {
        Car car = new Car("Nissan", 4);
        carDao.save(car);
        List<Car> carsList = carDao.getAllCarsByCompanyId(4);
        assertNotNull(carsList);
        assertEquals(carsList.size(), 2);
    }

    @Test()
    public void shouldGetByIdAndReturnExpectedCar() {
        Car firstCar = carDao.getById(1);
        Car secondCar = carDao.getById(2);
        Car thirdCar = carDao.getById(3);

        assertNotNull(firstCar);
        assertEquals(firstCar.getId(), 1);
        assertEquals(firstCar.getName(), "BMW");
        assertEquals(firstCar.getCompanyId(), 1);

        assertNotNull(secondCar);
        assertEquals(secondCar.getId(), 2);
        assertEquals(secondCar.getName(), "Audi");
        assertEquals(secondCar.getCompanyId(), 2);

        assertNotNull(thirdCar);
        assertEquals(thirdCar.getId(), 3);
        assertEquals(thirdCar.getName(), "Toyota");
        assertEquals(thirdCar.getCompanyId(), 3);
    }

    @Test()
    public void shouldUpdateCar() {
        Car car = carDao.getById(1);
        car.setRented(true);
        carDao.update(car);
        assertTrue(carDao.getById(1).isRented());
    }
}
