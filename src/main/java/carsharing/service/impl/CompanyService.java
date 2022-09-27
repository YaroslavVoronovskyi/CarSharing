package carsharing.service.impl;

import carsharing.model.Company;
import carsharing.dao.ICompanyDao;
import carsharing.service.ICompanyService;

import java.util.List;

public class CompanyService implements ICompanyService {

    private final ICompanyDao companyDao;

    public CompanyService(ICompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    @Override
    public Company getById(int id) {
        return companyDao.getById(id);
    }

    @Override
    public List<Company> getAll() {
        return companyDao.getAll();
    }

    @Override
    public void save(Company company) {
        companyDao.save(company);
    }
}
