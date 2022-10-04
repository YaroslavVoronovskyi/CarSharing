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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
    public void methodShouldReturnExpectedCompany() {
        Company expectedCompany = companyService.getById(1);
        Company actualCompany = createFakeCompany();
        assertNotNull(expectedCompany);
        assertEquals(expectedCompany.getName(), actualCompany.getName());
    }

    @Test
    public void methodShouldReturnExpectedCompaniesList() {
        List<Company> expectedCompaniesList = companyService.getAll();
        List<Company> actualCompaniesList = createFakeCompaniesList();
        assertNotNull(expectedCompaniesList);
        assertEquals(expectedCompaniesList.size(), 3);
        assertEquals(expectedCompaniesList.get(0).getId(), actualCompaniesList.get(0).getId());
        assertEquals(expectedCompaniesList.get(0).getName(), actualCompaniesList.get(0).getName());
    }

    @Test
    public void methodShouldSaveCompany() {
        Company company = new Company("Avis");
        company.setId(4);
        companyService.save(company);
        Mockito.verify(companyDaoMock, Mockito.times(1)).save(company);
    }

    private void initMocksForCompanies() {
        Mockito.when(companyDaoMock.getAll()).thenReturn(createFakeCompaniesList());
    }

    private void initMocksForCompany() {
        Mockito.when(companyDaoMock.getById(1)).thenReturn(createFakeCompany());
    }

    private Company createFakeCompany() {
        Company company = new Company("SIXT");
        company.setId(1);
        return company;
    }

    private List<Company> createFakeCompaniesList() {
        List<Company> companiesList = new ArrayList<>();

        Company firstCompany = new Company("SIXT");
        Company secondCompany = new Company("OkCar");
        Company thirdCompany = new Company("EuropeCar");

        companiesList.add(firstCompany);
        companiesList.add(secondCompany);
        companiesList.add(thirdCompany);

        return companiesList;
    }
}
