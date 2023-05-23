package org.example._interface;

public interface Repository<T> {

    boolean create(T element);

    boolean update(T element);

    boolean delete(T element);

    T findById(int id);
}
