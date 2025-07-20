package ru.aks.restoran.api;

import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.aks.restoran.dto.auth.JwtResponse;
import ru.aks.restoran.dto.auth.LoginRequest;
import ru.aks.restoran.dto.user.UserRequest;
import ru.aks.restoran.service.impl.AuthServImpl;


@RestController
@RequestMapping("/api/auth")

public class AuthController {

    private final AuthServImpl authServ;


    public AuthController( AuthServImpl authServ) {
        this.authServ= authServ;

    }

    @PostMapping("/login")
    public JwtResponse login(@RequestBody LoginRequest request) {
      return   authServ.sign_in(request);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid UserRequest request) throws BadRequestException {
        authServ.saveAsApplication(request);
        return ResponseEntity.ok("Заявка отправлена");
    }

}
