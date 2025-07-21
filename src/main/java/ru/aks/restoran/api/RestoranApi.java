package ru.aks.restoran.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.aks.restoran.dto.RestoranDto.RestoranRequest;
import ru.aks.restoran.dto.RestoranDto.RestoranResponse;
import ru.aks.restoran.dto.SimpleResponse;
import ru.aks.restoran.service.RestoranServ;

import java.util.List;

@RestController

@RequestMapping("api/restorans")
public class RestoranApi {
    private final RestoranServ restoranServ;

    public RestoranApi(RestoranServ restoranServ) {
        this.restoranServ = restoranServ;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<RestoranResponse> allRestaurants() {
        return restoranServ.getAllRestaurants();
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public SimpleResponse saveRestaurant(@RequestBody RestoranRequest restoranRequest) {

        return restoranServ.save(restoranRequest);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public RestoranResponse getRestaurantByID(@PathVariable Long id) {
        return restoranServ.getById(id);
    }
}
