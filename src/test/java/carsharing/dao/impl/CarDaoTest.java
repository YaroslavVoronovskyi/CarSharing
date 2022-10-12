package carsharing.dao.impl;

import carsharing.Constants;
import carsharing.connection.DataSource;
import carsharing.model.Car;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CarDaoTest {

    private final static String TEST_CAR_NAME = "BMW";
    private final static int TEST_CAR_ID = 1;
    private final static int TEST_COMPANY_ID = 1;
    @Mock
    private Connection connectionMock;
    @Mock
    private PreparedStatement statementMock;
    @Mock
    private ResultSet resultSetMock;
    @InjectMocks
    private CarDao carDao;

    @Test
    public void shouldSaveNewCar() throws SQLException {
        try (MockedStatic<DataSource> mockStatic = Mockito.mockStatic(DataSource.class)) {
            mockStatic.when(DataSource::getConnection).thenReturn(connectionMock);
            Mockito.when(connectionMock.prepareStatement(Constants.Queries.CAR_SAVE_QUERY)).thenReturn(statementMock);
            Mockito.when(statementMock.execute()).thenReturn(true);

            carDao.save(new Car(TEST_CAR_NAME, TEST_COMPANY_ID));

            Mockito.verify(statementMock).execute();
        }
    }

    @Test
    public void shouldUpdateCar() throws SQLException {
        try (MockedStatic<DataSource> mockStatic = Mockito.mockStatic(DataSource.class)) {
            mockStatic.when(DataSource::getConnection).thenReturn(connectionMock);
            Mockito.when(connectionMock.prepareStatement(Constants.Queries.CAR_UPDATE_QUERY)).thenReturn(statementMock);
            Mockito.when(statementMock.executeUpdate()).thenReturn(1, 1);

            carDao.update(createTestCar());

            Mockito.verify(statementMock).executeUpdate();
        }
    }

    @Test
    public void shouldGetCarsListByCompanyId() throws SQLException {
        try (MockedStatic<DataSource> mockStatic = Mockito.mockStatic(DataSource.class)) {
            mockStatic.when(DataSource::getConnection).thenReturn(connectionMock);
            Mockito.when(connectionMock.prepareStatement(Constants.Queries.CAR_GET_ALL_QUERY_BY_COMPANY_ID)).thenReturn(statementMock);
            Mockito.when(statementMock.executeQuery()).thenReturn(resultSetMock);
            Mockito.when(resultSetMock.next()).thenReturn(true, false);
            Mockito.when(resultSetMock.getInt(Constants.FIRST_COLUMN_INDEX)).thenReturn(TEST_CAR_ID);
            Mockito.when(resultSetMock.getString(Constants.SECOND_COLUMN_INDEX)).thenReturn(TEST_CAR_NAME);
            Mockito.when(resultSetMock.getInt(Constants.THIRD_COLUMN_INDEX)).thenReturn(TEST_COMPANY_ID);
            List<Car> expectedCustomersList = List.of(createTestCar());

            List<Car> actualCustomersList = carDao.getAllCarsByCompanyId(TEST_COMPANY_ID);

            assertEquals(expectedCustomersList, actualCustomersList);
        }
    }

    private Car createTestCar() {
        Car car = new Car(TEST_CAR_NAME, TEST_COMPANY_ID);
        car.setId(TEST_CAR_ID);
        return car;
    }
}
