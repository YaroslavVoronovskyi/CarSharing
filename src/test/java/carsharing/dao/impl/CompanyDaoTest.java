package carsharing.dao.impl;

import carsharing.Constants;
import carsharing.connection.DataSource;
import carsharing.model.Company;
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
public class CompanyDaoTest {

    @Mock
    private Connection connectionMock;
    @Mock
    private PreparedStatement statementMock;
    @Mock
    private ResultSet resultSetMock;
    @InjectMocks
    private CompanyDao companyDao;

    @Test
    public void shouldSaveNewCompany() throws SQLException {
        try (MockedStatic<DataSource> mockStatic = Mockito.mockStatic(DataSource.class)) {
            mockStatic.when(DataSource::getConnection).thenReturn(connectionMock);
            Mockito.when(connectionMock.prepareStatement(Constants.Queries.COMPANY_SAVE_QUERY)).thenReturn(statementMock);
            Mockito.when(statementMock.execute()).thenReturn(true);

            companyDao.save(new Company("SIXT"));

            Mockito.verify(connectionMock).prepareStatement(Constants.Queries.COMPANY_SAVE_QUERY);
            Mockito.verify(statementMock).execute();
        }
    }

    @Test
    public void shouldGetExpectedCompaniesList() throws SQLException {
        try (MockedStatic<DataSource> mockStatic = Mockito.mockStatic(DataSource.class)) {
            mockStatic.when(DataSource::getConnection).thenReturn(connectionMock);
            Mockito.when(connectionMock.prepareStatement(Constants.Queries.COMPANY_GET_ALL_QUERY)).thenReturn(statementMock);
            Mockito.when(statementMock.executeQuery()).thenReturn(resultSetMock);
            Mockito.when(resultSetMock.next()).thenReturn(true, false);

            List<Company> companiesList = companyDao.getAll();

            Mockito.verify(connectionMock).prepareStatement(Constants.Queries.COMPANY_GET_ALL_QUERY);
            Mockito.verify(statementMock).executeQuery();
            Mockito.verify(resultSetMock, Mockito.times(2)).next();

            assertNotNull(companiesList);
            assertEquals(companiesList.size(), 1);
        }
    }

    @Test
    public void shouldGetExpectedCompanyById() throws SQLException {
        try (MockedStatic<DataSource> mockStatic = Mockito.mockStatic(DataSource.class)) {
            mockStatic.when(DataSource::getConnection).thenReturn(connectionMock);
            Mockito.when(connectionMock.prepareStatement(Constants.Queries.COMPANY_GET_QUERY)).thenReturn(statementMock);
            Mockito.when(statementMock.executeQuery()).thenReturn(resultSetMock);
            Mockito.when(resultSetMock.next()).thenReturn(true, false);

            Company company = companyDao.getById(0);

            Mockito.verify(connectionMock).prepareStatement(Constants.Queries.COMPANY_GET_QUERY);
            Mockito.verify(statementMock).executeQuery();
            Mockito.verify(resultSetMock, Mockito.times(2)).next();

            assertNotNull(company);
            assertEquals(company.getId(), 0);
        }
    }
}
