package ru.aks.restoran.service;

import ru.aks.restoran.dto.user.UserResponse;

import java.util.List;

public interface UserServ {
    List<UserResponse> getPendingApplications();

    UserResponse approveApplication(Long userId, Long restaurantId);

    UserResponse rejectApplication(Long userId);
}
