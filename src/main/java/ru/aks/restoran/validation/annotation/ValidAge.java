package ru.aks.restoran.validation.annotation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ru.aks.restoran.validation.validator.AgeValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AgeValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidAge {
    String message() default "Несоответсвие возвраста!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}