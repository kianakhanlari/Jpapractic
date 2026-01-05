package ir.maktab.util;

import jakarta.validation.*;

import java.util.Set;
import java.util.stream.Collectors;

public class BeanValidator {

    private static BeanValidator instance;

    private final Validator validator;


    private BeanValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    public static  BeanValidator getInstance() {
        if (instance == null) {
            instance = new BeanValidator();
        }
        return instance;
    }

    public <T> void validate(T object) {
        Set<ConstraintViolation<T>> violations = validator.validate(object);
        if (!violations.isEmpty()) {
            String message = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining());
            throw new ConstraintViolationException(message, violations);
        }

    }
}
