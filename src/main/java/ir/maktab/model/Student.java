package ir.maktab.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Entity
@Setter
@Getter
/*@DiscriminatorValue("STUDENT")*/

public class Student extends Person{
    @NotNull(message = "Student code cannot be null")
    private Long student_Code;
    private String field;
    private Date begin_Year;

}
