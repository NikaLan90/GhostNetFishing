package de.ghostnet.dao;

import java.util.List;

import de.ghostnet.model.GhostNet;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class GhostNetDAO {

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("ghostnetPU");

    // Speichert ein neues Geisternetz dauerhaft in der Datenbank.
    public void save(GhostNet ghostNet) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(ghostNet);
            entityManager.getTransaction().commit();
        } catch (RuntimeException exception) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw exception;
        } finally {
            entityManager.close();
        }
    }

    // Lädt alle gespeicherten Geisternetze aus der Datenbank.
    public List<GhostNet> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            return entityManager
                    .createQuery("SELECT g FROM GhostNet g ORDER BY g.id", GhostNet.class)
                    .getResultList();
        } finally {
            entityManager.close();
        }
    }

    // Sucht ein bestimmtes Geisternetz anhand seiner ID.
    public GhostNet findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            return entityManager.find(GhostNet.class, id);
        } finally {
            entityManager.close();
        }
    }

    // Aktualisiert ein bereits gespeichertes Geisternetz.
    public void update(GhostNet ghostNet) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.merge(ghostNet);
            entityManager.getTransaction().commit();
        } catch (RuntimeException exception) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw exception;
        } finally {
            entityManager.close();
        }
    }
}