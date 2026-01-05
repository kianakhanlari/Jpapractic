package ir.maktab.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
/*@DiscriminatorValue("TEACHER")*/
public class Teacher extends Person{
    @NotNull(message = "techer code cannot be null")
    private Long teacher_Code;
    private String license;
    @Enumerated(EnumType.STRING)
    private Degree degree;
    private Double salary;

}
