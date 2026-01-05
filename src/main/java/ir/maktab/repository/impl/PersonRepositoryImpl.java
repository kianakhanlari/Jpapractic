package ir.maktab.repository.impl;


import ir.maktab.model.Person;
import ir.maktab.model.Student;
import ir.maktab.repository.PersonRepository;
import ir.maktab.service.TransactionManagerImpl;
import ir.maktab.util.BeanValidator;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class PersonRepositoryImpl implements PersonRepository {

    private final TransactionManagerImpl transactionManager;

    public PersonRepositoryImpl(TransactionManagerImpl transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Override
    public void save(Person person) {
        transactionManager.getEntityManager().persist(person);
    }



    @Override
    public void update(Student student) {
   transactionManager.getEntityManager().merge(student);

    }

    @Override
    public void delete(Student student) {
        transactionManager.getEntityManager().remove(student);
    }

    @Override
    public List<Student> findAllStudent() {
        return transactionManager.getEntityManager()
                .createQuery("SELECT s FROM Student s", Student.class)
                .getResultList();
    }

    @Override
    public Optional<Student> findByStudentCode(Long studentCode) {
      return Optional.ofNullable(transactionManager.getEntityManager()
              .createQuery("SELECT s FROM Student s WHERE s.student_Code = :studentCode", Student.class)
              .setParameter("studentCode", studentCode)
              .getSingleResult());
    }

    @Override
    public List<Person> findAllPerson(){
        return transactionManager.getEntityManager()
                .createQuery("select p from Person p",Person.class)
                .getResultList();
    }


    @Override
    public Optional<Person> findById() {
        return Optional.empty();
    }


}
