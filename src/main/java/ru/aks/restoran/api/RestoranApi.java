package ru.aks.restoran.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.aks.restoran.dto.RestoranDto.RestoranRequest;
import ru.aks.restoran.dto.SimpleResponse;
import ru.aks.restoran.service.RestoranServ;

@RestController

@RequestMapping("api/restorans")
public class RestoranApi {
    private final RestoranServ restoranServ;

    public RestoranApi(RestoranServ restoranServ) {
        this.restoranServ = restoranServ;
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public SimpleResponse saveRestaurant (@RequestBody RestoranRequest restoranRequest) {

        return restoranServ.save(restoranRequest);
    }
}
