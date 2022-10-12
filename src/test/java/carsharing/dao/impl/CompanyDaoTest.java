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

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CompanyDaoTest {

    private final static String TEST_COMPANY_NAME = "SIXT";
    private final static int TEST_COMPANY_ID = 1;
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

            companyDao.save(new Company(TEST_COMPANY_NAME));

            Mockito.verify(statementMock).execute();
        }
    }

    @Test
    public void shouldGetExpectedCompaniesList() throws SQLException {
        try (MockedStatic<DataSource> mockStatic = Mockito.mockStatic(DataSource.class)) {
            mockStatic.when(DataSource::getConnection).thenReturn(connectionMock);
            Mockito.when(connectionMock.prepareStatement(Constants.Queries.COMPANY_GET_ALL_QUERY))
                    .thenReturn(statementMock);
            Mockito.when(statementMock.executeQuery()).thenReturn(resultSetMock);
            Mockito.when(resultSetMock.next()).thenReturn(true, false);
            Mockito.when(resultSetMock.getInt(Constants.FIRST_COLUMN_INDEX)).thenReturn(TEST_COMPANY_ID);
            Mockito.when(resultSetMock.getString(Constants.SECOND_COLUMN_INDEX)).thenReturn(TEST_COMPANY_NAME);
            List<Company> expectedCompaniesList = List.of(createTestCompany());

            List<Company> actualCompaniesList = companyDao.getAll();

            assertEquals(expectedCompaniesList, actualCompaniesList);
        }
    }

    @Test
    public void shouldGetExpectedCompanyById() throws SQLException {
        try (MockedStatic<DataSource> mockStatic = Mockito.mockStatic(DataSource.class)) {
            mockStatic.when(DataSource::getConnection).thenReturn(connectionMock);
            Mockito.when(connectionMock.prepareStatement(Constants.Queries.COMPANY_GET_QUERY)).thenReturn(statementMock);
            Mockito.when(statementMock.executeQuery()).thenReturn(resultSetMock);
            Mockito.when(resultSetMock.next()).thenReturn(true, false);
            Mockito.when(resultSetMock.getInt(Constants.FIRST_COLUMN_INDEX)).thenReturn(TEST_COMPANY_ID);
            Mockito.when(resultSetMock.getString(Constants.SECOND_COLUMN_INDEX)).thenReturn(TEST_COMPANY_NAME);
            Company expectedCompany = createTestCompany();

            Company actualCompany = companyDao.getById(TEST_COMPANY_ID);

            assertEquals(expectedCompany, actualCompany);
        }
    }

    private Company createTestCompany() {
        Company company = new Company(TEST_COMPANY_NAME);
        company.setId(TEST_COMPANY_ID);
        return company;
    }
}
