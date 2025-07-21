package ru.aks.restoran.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.aks.restoran.dto.SimpleResponse;
import ru.aks.restoran.dto.user.UserResponse;
import ru.aks.restoran.entities.Restaurant;
import ru.aks.restoran.entities.User;
import ru.aks.restoran.enums.Role;
import ru.aks.restoran.mapper.UserMapper;
import ru.aks.restoran.repositories.RestoranRepo;
import ru.aks.restoran.repositories.UserRepo;
import ru.aks.restoran.service.UserServ;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UserServImpl implements UserServ {
    private final UserRepo userRepository;
    private final RestoranRepo restoranRepo;
    private final UserMapper userMapper;

    public UserServImpl(UserRepo userRepository, RestoranRepo restoranRepo, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.restoranRepo = restoranRepo;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserResponse> getPendingApplications() {

        List<UserResponse> pending = userRepository.findAll().stream()
                .filter(user -> !user.isApproved() && !user.isRejected() && user.getRole() != Role.ADMIN)
                //.map(this:: mapToResponse)
                .map(userMapper::requestDto)
                .collect(Collectors.toList());


        return pending;
    }

    @Override
    public SimpleResponse approveApplication(Long userId, Long restaurantId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        if (user.isApproved() || user.isRejected()) {
            throw new IllegalStateException("Already processed");
        }

        Restaurant restaurant = restoranRepo.findById(restaurantId)
                .orElseThrow(() -> new NoSuchElementException("Restaurant not found"));
        if (restoranRepo.findAll().size() >= 15) {
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "The vacancy is closed");
        }
        user.setRestaurant(restaurant);
        user.setApproved(true);
        userRepository.save(user);


        return new SimpleResponse(HttpStatus.OK, "Application is approved");
    }

    @Override
    public SimpleResponse rejectApplication(Long userId, Long restaurantId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        //  Just delete user ! Or :

        if (user.isApproved() || user.isRejected()) {
            throw new IllegalStateException("Already processed");
        }
        user.setRestaurant(null);
        user.setRejected(true);
        userRepository.save(user);

        mapToResponse(user);
        return new SimpleResponse(HttpStatus.OK, "Application is rejected!");
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



    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .filter(user ->user.getRestaurant() != null  && user.getRole()!=Role.ADMIN )
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserResponse> getEmployeesOfRestoran(Long restoranId) {
        List<UserResponse> pending = userRepository.findAll().stream()
                .filter(user-> user.getRestaurant() != null && user.getRestaurant().getId()==restoranId && user.getRole() != Role.ADMIN)
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        if (pending.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.OK, "There are no employees in this restoran!");
        }

        return pending;
    }
}
