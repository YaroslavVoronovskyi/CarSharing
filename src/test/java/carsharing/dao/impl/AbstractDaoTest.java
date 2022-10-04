package carsharing.dao.impl;

import carsharing.Initializer;
import carsharing.dao.ICarDao;
import carsharing.dao.ICompanyDao;
import carsharing.dao.ICustomerDao;
import carsharing.model.Car;
import carsharing.model.Company;
import carsharing.model.Customer;
import org.junit.jupiter.api.BeforeAll;

public class AbstractDaoTest {
    private static final ICompanyDao companyDao = new CompanyDao();
    private static final ICustomerDao customerDao = new CustomerDao();
    private static final ICarDao carDao = new CarDao();

    @BeforeAll
    public static void init() {
        Initializer.innitDataBase();

        customerDao.save(new Customer("Yaroslav"));
        customerDao.save(new Customer("Boris"));
        customerDao.save(new Customer("Piter"));
        customerDao.save(new Customer("Bill"));

        companyDao.save(new Company("SIXT"));
        companyDao.save(new Company("OkCars"));
        companyDao.save(new Company("CarToGo"));
        companyDao.save(new Company("Avis"));

        carDao.save(new Car("BMW", 1));
        carDao.save(new Car("Audi", 2));
        carDao.save(new Car("Toyota", 3));
        carDao.save(new Car("Subaru", 4));
    }
}
