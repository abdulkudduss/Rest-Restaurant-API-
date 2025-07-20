package ru.aks.restoran.service.impl;

import org.apache.coyote.BadRequestException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.aks.restoran.config.JwtUtil;
import ru.aks.restoran.dto.auth.JwtResponse;
import ru.aks.restoran.dto.auth.LoginRequest;
import ru.aks.restoran.dto.user.UserRequest;
import ru.aks.restoran.entities.Restaurant;
import ru.aks.restoran.entities.User;
import ru.aks.restoran.enums.Role;
import ru.aks.restoran.repositories.RestoranRepo;
import ru.aks.restoran.repositories.UserRepo;
import ru.aks.restoran.service.AuthServ;

import java.util.List;


@Service
public class AuthServImpl implements AuthServ {
    private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final RestoranRepo restoranRepo;

    public AuthServImpl(UserRepo userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authManager, JwtUtil jwtUtil, RestoranRepo restoranRepo) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.restoranRepo = restoranRepo;
    }

    public void initAdmin() {
        String adminEmail = "admin@gmail.com";

        if (!userRepository.existsByEmail(adminEmail)) {
            User admin = new User();
            admin.setFirstName("Admin");
            admin.setEmail(adminEmail);
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(Role.ADMIN);
            userRepository.save(admin);
            System.out.println(" Admin user created.");
        } else {
            System.out.println(" Admin already exists.");
        }
    }

    @Override
    public JwtResponse sign_in(LoginRequest request) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String email = userDetails.getUsername();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));


        String token = jwtUtil.generateToken(user);


        return new JwtResponse(token);


    }

    @Override
    public void saveAsApplication(UserRequest request) throws BadRequestException {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email уже используется");
        }
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhone(request.getPhone());
        user.setRole(request.getRole());
        user.setExperience(request.getExperience());

        user.setApproved(false);
        user.setRejected(false);
       Restaurant restaurant= restoranRepo.findById(request.getRestoranId())
               .orElseThrow(()-> new BadRequestException("Ресторан  не найден!!"));
      user.setRestaurant(restaurant);
       userRepository.save(user);
    }
}
