package ir.maktab.repository;

import ir.maktab.model.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository {
    void save(Teacher teacher);

    void update(Teacher teacher);

    void delete(Teacher teacher);

    Optional<Teacher> findTeacherByTeccherCode(Long teachercode);

    List<Teacher> showAllTeacher();


}
