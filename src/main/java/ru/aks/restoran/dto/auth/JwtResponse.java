package ru.aks.restoran.dto.auth;

import ru.aks.restoran.enums.Role;

public class JwtResponse {
    private String token;

    public JwtResponse(String token) { this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}