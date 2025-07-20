package ru.aks.restoran.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.aks.restoran.dto.user.UserRequest;
import ru.aks.restoran.enums.Role;
import ru.aks.restoran.validation.annotation.ValidExperience;

public class ExperienceValidator implements ConstraintValidator<ValidExperience, UserRequest> {

    @Override
    public boolean isValid(UserRequest request, ConstraintValidatorContext context) {
        if (request.getRole() == Role.CHEF && request.getExperience() < 3) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("опыт повара >=3")
                    .addPropertyNode("experience")
                    .addConstraintViolation();
            return false;
        }
        if (request.getRole() == Role.WAITER && request.getExperience() < 1) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("опыт официанта >=1")
                    .addPropertyNode("experience")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}