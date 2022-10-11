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
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CustomerDaoTest {

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

            customerDao.save(new Customer("Yaroslav"));

            Mockito.verify(connectionMock).prepareStatement(Constants.Queries.CUSTOMER_SAVE_QUERY);
            Mockito.verify(statementMock).execute();
        }
    }

    @Test
    public void shouldUpdateCustomer() throws SQLException {
        try (MockedStatic<DataSource> mockStatic = Mockito.mockStatic(DataSource.class)) {
            mockStatic.when(DataSource::getConnection).thenReturn(connectionMock);
            Mockito.when(connectionMock.prepareStatement(Constants.Queries.CUSTOMER_UPDATE_QUERY)).thenReturn(statementMock);
            Mockito.when(statementMock.executeUpdate()).thenReturn(1, 1);

            customerDao.update(testCustomerForUpdate());

            Mockito.verify(connectionMock).prepareStatement(Constants.Queries.CUSTOMER_UPDATE_QUERY);
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

            List<Customer> customersList = customerDao.getAll();

            Mockito.verify(connectionMock).prepareStatement(Constants.Queries.CUSTOMER_GET_ALL_QUERY);
            Mockito.verify(statementMock).executeQuery();
            Mockito.verify(resultSetMock, Mockito.times(2)).next();

            assertNotNull(customersList);
            assertEquals(customersList.size(), 1);
        }
    }

    @Test
    public void shouldGetExpectedCustomerById() throws SQLException {
        try (MockedStatic<DataSource> mockStatic = Mockito.mockStatic(DataSource.class)) {
            mockStatic.when(DataSource::getConnection).thenReturn(connectionMock);
            Mockito.when(connectionMock.prepareStatement(Constants.Queries.CUSTOMER_GET_QUERY)).thenReturn(statementMock);
            Mockito.when(statementMock.executeQuery()).thenReturn(resultSetMock);
            Mockito.when(resultSetMock.next()).thenReturn(true, false);

            Customer customer = customerDao.getById(0);

            Mockito.verify(connectionMock).prepareStatement(Constants.Queries.CUSTOMER_GET_QUERY);
            Mockito.verify(statementMock).executeQuery();
            Mockito.verify(resultSetMock, Mockito.times(2)).next();

            assertNotNull(customer);
            assertEquals(customer.getId(), 0);
            assertEquals(customer.getRentedCarId(), 0);
        }
    }

    private Customer testCustomerForUpdate() {
        Customer customer = new Customer("Yaroslav");
        customer.setId(1);
        customer.setRentedCarId(1);
        return customer;
    }
}
