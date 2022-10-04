package carsharing.processors.impl.car;

import carsharing.model.Car;
import carsharing.service.ICarService;
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

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ShowCarsListProcessorTest {

    @Mock
    private ICarService carServiceMock;
    @InjectMocks
    private ShowCarsListProcessor showCarsListProcessor;

    @Test
    public void shouldReturnSupportedActionTitle() {
        assertEquals(showCarsListProcessor.getSupportedCarActionTitle(), "1");
    }

    @Test
    public void shouldShowCarsList() {
        Mockito.when(carServiceMock.getAllCarsByCompanyId(1)).thenReturn(createFakeCarsList());
        showCarsListProcessor.doActionWithCar(1);
        Mockito.verify(carServiceMock, Mockito.times(1)).getAllCarsByCompanyId(1);
    }

    @Test
    public void shouldShowMessageThatListEmpty() {
        Mockito.when(carServiceMock.getAllCarsByCompanyId(1)).thenReturn(new ArrayList<>());
        showCarsListProcessor.doActionWithCar(1);
        Mockito.verify(carServiceMock, Mockito.times(1)).getAllCarsByCompanyId(1);
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
