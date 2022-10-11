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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CarDaoTest {

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

            carDao.save(new Car("BMW", 1));

            Mockito.verify(connectionMock).prepareStatement(Constants.Queries.CAR_SAVE_QUERY);
            Mockito.verify(statementMock).execute();
        }
    }

    @Test
    public void shouldUpdateCar() throws SQLException {
        try (MockedStatic<DataSource> mockStatic = Mockito.mockStatic(DataSource.class)) {
            mockStatic.when(DataSource::getConnection).thenReturn(connectionMock);
            Mockito.when(connectionMock.prepareStatement(Constants.Queries.CAR_UPDATE_QUERY)).thenReturn(statementMock);
            Mockito.when(statementMock.executeUpdate()).thenReturn(1, 1);

            carDao.update(testCarForUpdate());

            Mockito.verify(connectionMock).prepareStatement(Constants.Queries.CAR_UPDATE_QUERY);
            Mockito.verify(statementMock).executeUpdate();
        }
    }

    @Test
    public void shouldGetExpectedCarsListByCompanyId() throws SQLException {
        try (MockedStatic<DataSource> mockStatic = Mockito.mockStatic(DataSource.class)) {
            mockStatic.when(DataSource::getConnection).thenReturn(connectionMock);
            Mockito.when(connectionMock.prepareStatement(Constants.Queries.CAR_GET_ALL_QUERY_BY_COMPANY_ID)).thenReturn(statementMock);
            Mockito.when(statementMock.executeQuery()).thenReturn(resultSetMock);
            Mockito.when(resultSetMock.next()).thenReturn(true, false);

            List<Car> customersList = carDao.getAllCarsByCompanyId(1);

            Mockito.verify(connectionMock).prepareStatement(Constants.Queries.CAR_GET_ALL_QUERY_BY_COMPANY_ID);
            Mockito.verify(statementMock).executeQuery();
            Mockito.verify(resultSetMock, Mockito.times(2)).next();

            assertNotNull(customersList);
            assertEquals(customersList.size(), 1);
        }
    }

    private Car testCarForUpdate() {
        Car car = new Car("BMW", 1);
        car.setId(1);
        return car;
    }
}
