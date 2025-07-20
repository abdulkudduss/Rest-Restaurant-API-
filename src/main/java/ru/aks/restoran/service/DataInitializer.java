package ru.aks.restoran.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.aks.restoran.service.impl.AuthServImpl;


@Component
public class DataInitializer implements CommandLineRunner {

    private final AuthServImpl authServ;

    public DataInitializer(AuthServImpl authServ) {
        this.authServ = authServ;
    }

    @Override
    public void run(String... args) {
        authServ.initAdmin();
    }
}
