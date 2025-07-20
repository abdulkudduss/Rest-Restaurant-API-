package ru.aks.restoran.validation.annotation;

import jakarta.validation.Constraint;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ru.aks.restoran.validation.validator.AgeValidator;
import ru.aks.restoran.validation.validator.NoAdminRoleValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NoAdminRoleValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NoAdminRole {
    String message() default "Роль ADMIN нельзя указать вручную \n" +
            "Выбери из [  CHEF, WAITER]";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}