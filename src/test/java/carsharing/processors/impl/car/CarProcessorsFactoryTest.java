package carsharing.processors.impl.car;

import carsharing.dao.ICarDao;
import carsharing.dao.impl.CarDao;
import carsharing.processors.ICarProcessor;
import carsharing.processors.ICarProcessorsFactory;
import carsharing.service.ICarService;
import carsharing.service.impl.CarService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CarProcessorsFactoryTest {

    @Test
    public void shouldReturnSupportedActionTitle() {

        ICarDao carDao = new CarDao();
        ICarService carService = new CarService(carDao);

        ICarProcessor showCarsListProcessor = new ShowCarsListProcessor(carService);
        ICarProcessor createNewCarProcessor = new CreateNewCarProcessor(carService);
        ICarProcessor backFromCarMenuToPreviousMenuProcessor = new BackFromCarMenuToPreviousMenuProcessor();

        ICarProcessorsFactory carProcessorsFactory = new CarProcessorsFactory(
                List.of(showCarsListProcessor, createNewCarProcessor, backFromCarMenuToPreviousMenuProcessor));

        assertNotNull(carProcessorsFactory.getCarProcessorByAction("0"));
        assertNotNull(carProcessorsFactory.getCarProcessorByAction("1"));
        assertNotNull(carProcessorsFactory.getCarProcessorByAction("2"));

        assertNull(carProcessorsFactory.getCarProcessorByAction("3"));
        assertNull(carProcessorsFactory.getCarProcessorByAction("4"));
        assertNull(carProcessorsFactory.getCarProcessorByAction("5"));
        assertNull(carProcessorsFactory.getCarProcessorByAction("6"));
    }
}
