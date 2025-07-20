package ru.aks.restoran.service.impl;

import ru.aks.restoran.dto.user.UserResponse;
import ru.aks.restoran.entities.Restaurant;
import ru.aks.restoran.entities.User;
import ru.aks.restoran.repositories.RestoranRepo;
import ru.aks.restoran.repositories.UserRepo;
import ru.aks.restoran.service.UserServ;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class UserServImpl implements UserServ {
    private final UserRepo userRepository;
    private final RestoranRepo restoranRepo;

    public UserServImpl(UserRepo userRepository, RestoranRepo restoranRepo) {
        this.userRepository = userRepository;
        this.restoranRepo = restoranRepo;
    }

    @Override
    public List<UserResponse> getPendingApplications() {
        return userRepository.findAll().stream()
                .filter(user -> !user.isApproved() && !user.isRejected())
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse approveApplication(Long userId, Long restaurantId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        if (user.isApproved() || user.isRejected()) {
            throw new IllegalStateException("Already processed");
        }

        Restaurant restaurant = restoranRepo.findById(restaurantId)
                .orElseThrow(() -> new NoSuchElementException("Restaurant not found"));

        user.setRestaurant(restaurant);
        user.setApproved(true);
        userRepository.save(user);

        return mapToResponse(user);
    }

    @Override
    public UserResponse rejectApplication(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        //  Just delete user ! Or :

        if (user.isApproved() || user.isRejected()) {
            throw new IllegalStateException("Already processed");
        }

        user.setRejected(true);
        userRepository.save(user);

        return mapToResponse(user);
    }

    private UserResponse mapToResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setFullName(user.getFirstName() + " " + user.getLastName());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());
        response.setExperience(user.getExperience());
        response.setApproved(user.isApproved());
        response.setRejected(user.isRejected());
        return response;
    }
}
