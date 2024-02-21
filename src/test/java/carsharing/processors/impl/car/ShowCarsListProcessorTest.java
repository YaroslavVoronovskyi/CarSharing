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

    private final static String TEST_SUPPORTED_ACTION_TITLE = "1";
    private final static String TEST_CAR_NAME = "BMW";
    private final static int TEST_COMPANY_ID = 1;
    @Mock
    private ICarService carServiceMock;
    @InjectMocks
    private ShowCarsListProcessor showCarsListProcessor;

    @Test
    public void shouldReturnSupportedActionTitle() {
        assertEquals(showCarsListProcessor.getSupportedCarActionTitle(), TEST_SUPPORTED_ACTION_TITLE);
    }

    @Test
    public void shouldCheckShowCarsListProcessor() {
        Mockito.when(carServiceMock.getAllCarsByCompanyId(TEST_COMPANY_ID)).thenReturn(List.of(createTestCarsList()));
        showCarsListProcessor.doActionWithCar(TEST_COMPANY_ID);
        Mockito.verify(carServiceMock).getAllCarsByCompanyId(TEST_COMPANY_ID);
    }

    @Test
    public void shouldShowMessageThatListEmpty() {
        Mockito.when(carServiceMock.getAllCarsByCompanyId(TEST_COMPANY_ID)).thenReturn(new ArrayList<>());
        showCarsListProcessor.doActionWithCar(TEST_COMPANY_ID);
        Mockito.verify(carServiceMock).getAllCarsByCompanyId(TEST_COMPANY_ID);
    }

    private Car createTestCarsList() {
        return new Car(TEST_CAR_NAME, TEST_COMPANY_ID);
    }
}
