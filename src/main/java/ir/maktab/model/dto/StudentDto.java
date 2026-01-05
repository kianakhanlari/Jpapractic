package ir.maktab.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;
import java.util.Date;

public class StudentDto {

    public String firstName;
    public String lastName;
    public LocalDate birthdate;
    public Long student_Code;
    public String field;
    public Date begin_Year;

}
