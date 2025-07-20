package ru.aks.restoran.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.aks.restoran.dto.user.UserRequest;
import ru.aks.restoran.enums.Role;
import ru.aks.restoran.validation.annotation.ValidAge;
import ru.aks.restoran.validation.annotation.ValidExperience;

import java.time.LocalDate;
import java.time.Period;

public class AgeValidator implements ConstraintValidator<ValidAge, UserRequest> {

    @Override
    public boolean isValid(UserRequest request, ConstraintValidatorContext context) {
        int age = Period.between(request.getBirthDate(), LocalDate.now()).getYears();
        if (request.getRole() == Role.CHEF && (age>45 || age<18)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("45>Возраст повара >18")
                    .addPropertyNode("birthDate")
                    .addConstraintViolation();
            return false;
        }
        if (request.getRole() == Role.WAITER && (age>30| age<18)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("30>Возраст официфнта >18")
                    .addPropertyNode("birthDate")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
