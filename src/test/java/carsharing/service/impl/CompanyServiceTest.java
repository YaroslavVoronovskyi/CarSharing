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
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CompanyServiceTest {

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
        Company expectedCompany = companyService.getById(1);
        Company actualCompany = createTestCompany();
        assertNotNull(expectedCompany);
        assertEquals(expectedCompany.getName(), actualCompany.getName());
    }

    @Test
    public void shouldReturnExpectedCompaniesList() {
        List<Company> expectedCompaniesList = companyService.getAll();
        List<Company> actualCompaniesList = List.of(createTestCompany());
        assertNotNull(expectedCompaniesList);
        assertEquals(expectedCompaniesList.size(), 1);
        assertEquals(expectedCompaniesList.get(0).getId(), actualCompaniesList.get(0).getId());
        assertEquals(expectedCompaniesList.get(0).getName(), actualCompaniesList.get(0).getName());
    }

    @Test
    public void shouldSaveCompany() {
        Company company = new Company("Avis");
        company.setId(4);
        companyService.save(company);
        Mockito.verify(companyDaoMock).save(company);
    }

    private void initMocksForCompanies() {
        Mockito.when(companyDaoMock.getAll()).thenReturn(List.of(createTestCompany()));
    }

    private void initMocksForCompany() {
        Mockito.when(companyDaoMock.getById(1)).thenReturn(createTestCompany());
    }

    private Company createTestCompany() {
        Company company = new Company("SIXT");
        company.setId(1);
        return company;
    }
}
