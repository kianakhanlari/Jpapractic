package ir.maktab.repository;

import ir.maktab.model.Person;
import ir.maktab.model.Student;

import java.util.List;
import java.util.Optional;

public interface PersonRepository {

    void save(Person person);

    void update(Student student);

    void delete(Student student);
    List<Student> findAllStudent();
    Optional<Student> findByStudentCode(Long studentCode);

    Optional<Person> findById();

   List<Person> findAllPerson();

}
