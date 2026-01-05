package ir.maktab.repository.impl;

import ir.maktab.model.Student;
import ir.maktab.repository.StudentRepository;
import ir.maktab.service.TransactionManagerImpl;

public class StudentRepositoryImpl implements StudentRepository {
    private final TransactionManagerImpl transactionManager;

    public StudentRepositoryImpl(TransactionManagerImpl transactionManager) {
        this.transactionManager = transactionManager;
    }
    @Override
    public void save(Student student) {
        transactionManager.getEntityManager().persist(student);
    }
}
