package ir.maktab.service;

import ir.maktab.model.Person;
import ir.maktab.model.Student;
import ir.maktab.model.dto.StudentDto;
import ir.maktab.repository.PersonRepository;

import java.util.List;

public class PersonService {
    private final PersonRepository personRepository;
    private final TransactionManagerImpl transactionManager;


    public PersonService(PersonRepository personRepository, TransactionManagerImpl transactionManager) {
        this.personRepository = personRepository;
        this.transactionManager = transactionManager;
    }



    public void save(StudentDto dto) {
        try {
            transactionManager.begin();
            Student student=new Student();
            student.setField(dto.field);
            student.setFirstName(dto.firstName);
            student.setLastName(dto.lastName);
            student.setStudent_Code(dto.student_Code);
            personRepository.save(student);
            transactionManager.commit();
        } catch (Exception e) {
            transactionManager.rollback();
            throw e;
        }
    }
    public void update(StudentDto dto) {
        try {
            transactionManager.begin();

            Student student  = personRepository
                   .findByStudentCode(dto.student_Code)
                    .orElseThrow(() -> new RuntimeException("Student not found"));

           /* student.setField(dto.field);*/
            student.setFirstName(dto.firstName);
            student.setLastName(dto.lastName);

            personRepository.update(student);

            transactionManager.commit();
        } catch (Exception e) {
            transactionManager.rollback();
            throw e;
        }
    }
    public void delete(Long studentCode) {
        try {
            transactionManager.begin();


            Student student = personRepository
                    .findByStudentCode(studentCode)
                    .orElseThrow(() -> new RuntimeException("Student not found"));


            personRepository.delete(student);

            transactionManager.commit();
        } catch (Exception e) {
            transactionManager.rollback();
            throw e;
        }
    }
    public List<Student> showAllStudents(){
       return personRepository.findAllStudent();
    }
    public List<Person> showAllPerson(){
        return personRepository.findAllPerson();
    }

}