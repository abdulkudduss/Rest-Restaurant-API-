package ru.aks.restoran.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.aks.restoran.enums.Role;
import ru.aks.restoran.validation.annotation.NoAdminRole;

public class NoAdminRoleValidator implements ConstraintValidator<NoAdminRole, Role> {
    @Override
    public boolean isValid(Role role, ConstraintValidatorContext context) {
        return role != Role.ADMIN;
    }
}