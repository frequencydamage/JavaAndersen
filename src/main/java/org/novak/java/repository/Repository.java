package org.novak.java.repository;

import org.novak.java.util.ConfigReaderUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

public abstract class Repository {

    private final EntityManager entityManager;

    protected Repository() {
        Map<String, String> props = Map.of(
                "jakarta.persistence.jdbc.url", ConfigReaderUtil.getProperty("DB_URL"),
                "jakarta.persistence.jdbc.user", ConfigReaderUtil.getProperty("DB_USER"),
                "jakarta.persistence.jdbc.password", ConfigReaderUtil.getProperty("DB_PASS")
        );

        entityManager = Persistence
                .createEntityManagerFactory("workspace_manager_app_unit", props)
                .createEntityManager();
    }

    protected void create(Object entity) {
        entityManager.clear();
        executeTransaction(() -> entityManager.persist(entity));
        entityManager.refresh(entity);
    }

    protected void update(Object entity) {
        executeTransaction(() -> entityManager.merge(entity));
    }

    protected void delete(Object entity) {
        executeTransaction(() -> entityManager.remove(entity));
    }

    protected <T> T getById(Class<T> clazz, Integer id) {
        return entityManager.find(clazz, id);
    }

    protected <T> List<T> getAll(Class<T> clazz) {
        entityManager.clear();
        return entityManager.createQuery("SELECT entity FROM " + clazz.getSimpleName() + " entity", clazz)
                .getResultList();
    }

    protected <T> Query createQuery(String query, Class<T> clazz) {
        return entityManager.createQuery(query, clazz);
    }

    private void executeTransaction(Runnable action) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            action.run();
            transaction.commit();
        } catch (Exception ex) {
            if (transaction.isActive()) {
                transaction.rollback();
            }

            System.out.println("Transaction failed!" + ex.getMessage());
            throw ex;
        }
    }
}
