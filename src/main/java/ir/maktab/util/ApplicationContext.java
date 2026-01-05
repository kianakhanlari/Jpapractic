package ir.maktab.util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ApplicationContext {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("default");


    private ApplicationContext() {
    }
    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
    public static void close() {
        emf.close();
    }

}
