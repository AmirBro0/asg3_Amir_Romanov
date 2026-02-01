package repository.interfaces;

import java.util.List;

public interface CRUD<T, ID> {
    ID create(T entity);
    T findById(ID id);
    List<T> findAll();
    boolean update(T entity);
    boolean delete(ID id);
}
