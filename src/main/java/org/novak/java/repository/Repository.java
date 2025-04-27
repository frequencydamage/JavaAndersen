package org.novak.java.repository;

import jakarta.persistence.Query;

import java.util.List;

public interface Repository {

    void create(Object entity);

    void update(Object entity);

    void delete(Object entity);

    <T> List<T> getAll(Class<T> clazz);

    <T> Query createQuery(String query, Class<T> clazz);
}
