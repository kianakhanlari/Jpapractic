package ir.maktab.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class TransactionManagerImpl implements TransactionManager {
    private final EntityManagerFactory emf;
    private EntityManager em;

    public TransactionManagerImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void begin() {
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }

    @Override
    public void commit() {
        em.getTransaction().commit();
        em.close();
        em = null;

    }

    @Override
    public void rollback() {
        em.getTransaction().rollback();
        em.close();
        em = null;
    }
    public EntityManager getEntityManager() {
        if (em == null) {
            throw new IllegalStateException("Transaction not started. Call begin() first.");
        }
        return em;
    }
}
