package carsharing.dao;

import carsharing.model.Company;

import java.util.List;

public interface ICompanyDao {

    Company getById(int id);

    List<Company> getAll();

    void save(Company company);
}
