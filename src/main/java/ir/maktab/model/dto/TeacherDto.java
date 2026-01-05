package ir.maktab.model.dto;

import ir.maktab.model.Degree;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;

public class TeacherDto {
    public String firstName;
    public String lastName;
    public LocalDate birthdate;
    public Long teacher_Code;
    public String license;
    public Degree degree;
    public Double salary;

}
