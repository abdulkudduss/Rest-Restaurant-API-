package ru.aks.restoran.service;

import org.apache.coyote.BadRequestException;
import ru.aks.restoran.dto.auth.JwtResponse;
import ru.aks.restoran.dto.auth.LoginRequest;
import ru.aks.restoran.dto.user.UserRequest;

public interface AuthServ {
    JwtResponse sign_in(LoginRequest request);

    void saveAsApplication(UserRequest request) throws BadRequestException;
}
