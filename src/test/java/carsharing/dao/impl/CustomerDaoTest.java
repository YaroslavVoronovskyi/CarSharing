package carsharing.dao.impl;

import carsharing.Constants;
import carsharing.connection.DataSource;
import carsharing.model.Customer;
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
public class CustomerDaoTest {

    private final static String TEST_CUSTOMER_NAME = "Yaroslav";
    private final static int TEST_CUSTOMER_ID = 1;
    private final static Integer TEST_CUSTOMER_RENTED_CAR_ID = 1;
    @Mock
    private Connection connectionMock;
    @Mock
    private PreparedStatement statementMock;
    @Mock
    private ResultSet resultSetMock;
    @InjectMocks
    private CustomerDao customerDao;

    @Test
    public void shouldSaveNewCustomer() throws SQLException {
        try (MockedStatic<DataSource> mockStatic = Mockito.mockStatic(DataSource.class)) {
            mockStatic.when(DataSource::getConnection).thenReturn(connectionMock);
            Mockito.when(connectionMock.prepareStatement(Constants.Queries.CUSTOMER_SAVE_QUERY)).thenReturn(statementMock);
            Mockito.when(statementMock.execute()).thenReturn(true);

            customerDao.save(new Customer(TEST_CUSTOMER_NAME));

            Mockito.verify(statementMock).execute();
        }
    }

    @Test
    public void shouldUpdateCustomer() throws SQLException {
        try (MockedStatic<DataSource> mockStatic = Mockito.mockStatic(DataSource.class)) {
            mockStatic.when(DataSource::getConnection).thenReturn(connectionMock);
            Mockito.when(connectionMock.prepareStatement(Constants.Queries.CUSTOMER_UPDATE_QUERY)).thenReturn(statementMock);
            Mockito.when(statementMock.executeUpdate()).thenReturn(1, 1);

            customerDao.update(createTestCustomer());

            Mockito.verify(statementMock).executeUpdate();
        }
    }

    @Test
    public void shouldGetExpectedCustomersList() throws SQLException {
        try (MockedStatic<DataSource> mockStatic = Mockito.mockStatic(DataSource.class)) {
            mockStatic.when(DataSource::getConnection).thenReturn(connectionMock);
            Mockito.when(connectionMock.prepareStatement(Constants.Queries.CUSTOMER_GET_ALL_QUERY)).thenReturn(statementMock);
            Mockito.when(statementMock.executeQuery()).thenReturn(resultSetMock);
            Mockito.when(resultSetMock.next()).thenReturn(true, false);
            Mockito.when(resultSetMock.getInt(Constants.FIRST_COLUMN_INDEX)).thenReturn(TEST_CUSTOMER_ID);
            Mockito.when(resultSetMock.getString(Constants.SECOND_COLUMN_INDEX)).thenReturn(TEST_CUSTOMER_NAME);
            Mockito.when(resultSetMock.getObject(Constants.THIRD_COLUMN_INDEX, Integer.class))
                    .thenReturn(TEST_CUSTOMER_RENTED_CAR_ID);
            List<Customer> expectedCustomersList = List.of(createTestCustomer());

            List<Customer> actualCustomersList = customerDao.getAll();

            assertEquals(expectedCustomersList, actualCustomersList);
        }
    }

    @Test
    public void shouldGetExpectedCustomerById() throws SQLException {
        try (MockedStatic<DataSource> mockStatic = Mockito.mockStatic(DataSource.class)) {
            mockStatic.when(DataSource::getConnection).thenReturn(connectionMock);
            Mockito.when(connectionMock.prepareStatement(Constants.Queries.CUSTOMER_GET_QUERY)).thenReturn(statementMock);
            Mockito.when(statementMock.executeQuery()).thenReturn(resultSetMock);
            Mockito.when(resultSetMock.next()).thenReturn(true, false);
            Mockito.when(resultSetMock.getInt(Constants.FIRST_COLUMN_INDEX)).thenReturn(TEST_CUSTOMER_ID);
            Mockito.when(resultSetMock.getString(Constants.SECOND_COLUMN_INDEX)).thenReturn(TEST_CUSTOMER_NAME);
            Mockito.when(resultSetMock.getObject(Constants.THIRD_COLUMN_INDEX, Integer.class))
                    .thenReturn(TEST_CUSTOMER_RENTED_CAR_ID);
            Customer expectedCustomer = createTestCustomer();

            Customer actualCustomer = customerDao.getById(TEST_CUSTOMER_ID);

            assertEquals(expectedCustomer, actualCustomer);
        }
    }

    private Customer createTestCustomer() {
        Customer customer = new Customer(TEST_CUSTOMER_NAME);
        customer.setId(TEST_CUSTOMER_ID);
        customer.setRentedCarId(TEST_CUSTOMER_RENTED_CAR_ID);
        return customer;
    }
}
