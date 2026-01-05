package ir.maktab.repository.impl;

import ir.maktab.model.Student;
import ir.maktab.model.Teacher;
import ir.maktab.repository.TeacherRepository;
import ir.maktab.service.TransactionManagerImpl;

import java.util.List;
import java.util.Optional;

public class TeacherRepositoryImpl implements TeacherRepository {
    private final TransactionManagerImpl transactionManager;

    public TeacherRepositoryImpl(TransactionManagerImpl transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Override
    public void save(Teacher teacher) {
        transactionManager.getEntityManager().persist(teacher);
    }

    @Override
    public void update(Teacher teacher) {
        transactionManager.getEntityManager().merge(teacher);

    }

    @Override
    public void delete(Teacher teacher) {
        transactionManager.getEntityManager().remove(teacher);
    }

    @Override
    public Optional<Teacher> findTeacherByTeccherCode(Long teachercode) {

      return Optional.ofNullable(transactionManager.getEntityManager()
                .createQuery("SELECT t FROM Teacher t WHERE t.teacher_Code = :teacher_Code", Teacher.class)
                .setParameter("teacher_Code", teachercode)
                .getSingleResult());
    }

    @Override
    public List<Teacher> showAllTeacher() {
         return transactionManager.getEntityManager()
                .createQuery("SELECT t FROM Teacher  t", Teacher.class)
                .getResultList();
    }

}
