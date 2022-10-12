package carsharing.dao;

import java.util.List;

public interface IAbstractDao<T> {

    T getById(int id);
    List<T> getAll();
    void save(T model);
    void update(T model);
}
