package carsharing.dao;

import carsharing.model.Car;

import java.util.List;

public interface ICarDao extends IAbstractDao<Car> {

    List<Car> getAllCarsByCompanyId(int companyId);

}
