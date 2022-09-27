package carsharing.service;

import carsharing.model.Company;

import java.util.List;

public interface ICompanyService {

    Company getById(int id);

    List<Company> getAll();

    void save(Company company);
}
