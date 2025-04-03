package org.novak.java.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public abstract class RepositoryImpl implements Repository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Object entity) {
        entityManager.persist(entity);
    }

    public void update(Object entity) {
        entityManager.merge(entity);
    }

    public void delete(Object entity) {
        entityManager.remove(entity);
    }

    public <T> List<T> getAll(Class<T> clazz) {
        entityManager.clear();
        return entityManager.createQuery("SELECT entity FROM " + clazz.getSimpleName() + " entity", clazz)
                .getResultList();
    }

    public <T> Query createQuery(String query, Class<T> clazz) {
        return entityManager.createQuery(query, clazz);
    }

    public  <T> T getById(Class<T> clazz, Integer id) {
        return entityManager.find(clazz, id);
    }
}
