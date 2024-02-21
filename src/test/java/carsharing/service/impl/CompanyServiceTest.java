package carsharing.service.impl;

import carsharing.dao.ICompanyDao;
import carsharing.model.Company;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CompanyServiceTest {

    private final static String TEST_COMPANY_NAME = "SIXT";
    private final static int TEST_COMPANY_ID = 1;
    @Mock
    private ICompanyDao companyDaoMock;
    @InjectMocks
    private CompanyService companyService;

    @BeforeEach
    public void init() {
        initMocksForCompanies();
        initMocksForCompany();
    }

    @Test
    public void shouldReturnExpectedCompany() {
        Company expectedCompany = companyService.getById(TEST_COMPANY_ID);
        Company actualCompany = createTestCompany();
        assertEquals(expectedCompany, actualCompany);
    }

    @Test
    public void shouldReturnExpectedCompaniesList() {
        List<Company> expectedCompaniesList = companyService.getAll();
        List<Company> actualCompaniesList = List.of(createTestCompany());
        assertEquals(expectedCompaniesList, actualCompaniesList);
    }

    @Test
    public void shouldSaveCompany() {
        Company company = new Company(TEST_COMPANY_NAME);
        company.setId(TEST_COMPANY_ID);
        companyService.save(company);
        Mockito.verify(companyDaoMock).save(company);
    }

    private void initMocksForCompanies() {
        Mockito.when(companyDaoMock.getAll()).thenReturn(List.of(createTestCompany()));
    }

    private void initMocksForCompany() {
        Mockito.when(companyDaoMock.getById(TEST_COMPANY_ID)).thenReturn(createTestCompany());
    }

    private Company createTestCompany() {
        Company company = new Company(TEST_COMPANY_NAME);
        company.setId(TEST_COMPANY_ID);
        return company;
    }
}
