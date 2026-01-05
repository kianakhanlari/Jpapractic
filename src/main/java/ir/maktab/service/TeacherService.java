package ir.maktab.service;
import ir.maktab.model.Teacher;
import ir.maktab.model.dto.TeacherDto;
import ir.maktab.repository.TeacherRepository;

import java.util.List;

public class TeacherService {
    private final TransactionManagerImpl transactionManager;
    private final TeacherRepository teacherRepository;

    public TeacherService( TeacherRepository teacherRepository,TransactionManagerImpl transactionManager) {
        this.transactionManager = transactionManager;
        this.teacherRepository = teacherRepository;
    }
    public void save(TeacherDto dto) {
        try {
            transactionManager.begin();
            Teacher teacher=new Teacher();
            teacher.setTeacher_Code(dto.teacher_Code);
            teacher.setDegree(dto.degree);
            teacher.setSalary(dto.salary);
            teacher.setFirstName(dto.firstName);
            teacher.setLastName(dto.lastName);
            teacherRepository.save(teacher);
            transactionManager.commit();
        } catch (Exception e) {
            transactionManager.rollback();
            throw e;
        }
    }
    public void update(TeacherDto dto){
        try {
            transactionManager.begin();
            Teacher teacher=teacherRepository.
                    findTeacherByTeccherCode(dto.teacher_Code).orElseThrow();

          teacher.setLastName(dto.lastName);
          teacher.setFirstName(dto.firstName);
          teacher.setSalary(dto.salary);
          teacherRepository.update(teacher);
            transactionManager.commit();
        } catch (Exception e) {
            transactionManager.rollback();
            throw e;
        }
    }
    public void delete(Long teacherCode){
        try {
            transactionManager.begin();
            Teacher teacher=teacherRepository.
                    findTeacherByTeccherCode(teacherCode).orElseThrow();
            teacherRepository.delete(teacher);
            transactionManager.commit();
        } catch (Exception e) {
            transactionManager.rollback();
            throw e;
        }
        }
        public Teacher showTeacherByteacherCode(Long teacherCode)
        {
            return teacherRepository.
                    findTeacherByTeccherCode(teacherCode).orElseThrow();

        }
    public List<Teacher> showAllTeacher() {
        transactionManager.begin();
        List<Teacher> list = teacherRepository.showAllTeacher();
        transactionManager.commit();
        return list;
    }

}
