package ru.aks.restoran.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.aks.restoran.dto.SimpleResponse;
import ru.aks.restoran.dto.user.UserResponse;
import ru.aks.restoran.service.UserServ;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserApi {
    private final UserServ userServ;

    public UserApi(UserServ userServ) {
        this.userServ = userServ;
    }
    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getAllUsers(){
        return userServ.getAllUsers(); //
    }
@GetMapping("/restaurant/{id}")
    public List<UserResponse> getUsersByRestoran(@PathVariable Long id){
       return userServ.getEmployeesOfRestoran(id);
    }

    @GetMapping("/request")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getAllPendingApplications() {
        return userServ.getPendingApplications();
    }


    @PostMapping("/request/{userId}/approve")
    @PreAuthorize("hasRole('ADMIN')")
    public SimpleResponse approveApplication(@PathVariable Long userId,
                                             @RequestParam Long restoranId) {
        return userServ.approveApplication(userId, restoranId);
    }
    @PostMapping("/request/{userId}/reject")
    @PreAuthorize("hasRole('ADMIN')")
    public SimpleResponse rejectApplication(@PathVariable Long userId,
                                             @RequestParam Long restoranId) {
        return userServ.rejectApplication(userId, restoranId);
    }

}
