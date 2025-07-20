package ru.aks.restoran.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ru.aks.restoran.validation.validator.ExperienceValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExperienceValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidExperience {
    String message() default "Несоответсвие опыта!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}