package ru.aks.restoran.service;

import ru.aks.restoran.dto.SimpleResponse;
import ru.aks.restoran.dto.user.UserResponse;
import ru.aks.restoran.entities.User;

import java.util.List;

public interface UserServ {
    List<UserResponse> getPendingApplications();

    SimpleResponse approveApplication(Long userId, Long restaurantId);

    SimpleResponse rejectApplication(Long userId,Long restaurantId);

    List<UserResponse> getAllUsers();

    List<UserResponse> getEmployeesOfRestoran(Long restoranId);
}
