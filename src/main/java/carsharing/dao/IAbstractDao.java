package carsharing.dao;

import java.util.List;

public interface IAbstractDao<T> {

    T getById(int id);
    List<T> getAll();
    void save(T t);
    void update(T t);
}
