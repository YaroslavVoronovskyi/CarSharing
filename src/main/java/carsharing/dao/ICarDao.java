package carsharing.dao;

import carsharing.model.Car;

import java.util.List;

public interface ICarDao {

    Car getById(int id);

    List<Car> getAllCarsByCompanyId(int companyId);

    void save(Car car);

    void update(Car car);
}
