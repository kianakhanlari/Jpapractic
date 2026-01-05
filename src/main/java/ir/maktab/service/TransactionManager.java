package ir.maktab.service;

public interface TransactionManager {
    void begin();

    void commit();

    void rollback();

}
