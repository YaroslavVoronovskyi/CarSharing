package carsharing.dao.impl;

import carsharing.dao.ICompanyDao;
import carsharing.model.Company;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CompanyDaoTest extends AbstractDaoTest {

    private final ICompanyDao companyDao = new CompanyDao();

    @Test()
    public void shouldReturnExpectedNumberOfCompanies() {
        List<Company> companiesList = companyDao.getAll();
        assertNotNull(companiesList);
        assertEquals(companiesList.size(), 5);
    }

    @Test()
    public void shouldCreateNewCompaniesAndReturnExpectedNumberOfCompanies() {
        Company company = new Company("EuropeCar");
        companyDao.save(company);
        List<Company> companiesList = companyDao.getAll();
        assertNotNull(companiesList);
        assertEquals(companiesList.size(), 5);
    }


    @Test()
    public void shouldGetByIdAndReturnExpectedCompany() {
        Company firstCompany = companyDao.getById(1);
        Company secondCompany = companyDao.getById(2);
        Company thirdCompany = companyDao.getById(3);
        assertNotNull(firstCompany);
        assertEquals(firstCompany.getId(), 1);
        assertEquals(firstCompany.getName(), "SIXT");
        assertNotNull(secondCompany);
        assertEquals(secondCompany.getId(), 2);
        assertEquals(secondCompany.getName(), "OkCars");
        assertNotNull(thirdCompany);
        assertEquals(thirdCompany.getId(), 3);
        assertEquals(thirdCompany.getName(), "CarToGo");
    }
}
