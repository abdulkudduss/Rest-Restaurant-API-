package ru.aks.restoran.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.aks.restoran.dto.RestoranDto.RestoranRequest;
import ru.aks.restoran.dto.RestoranDto.RestoranResponse;
import ru.aks.restoran.dto.SimpleResponse;
import ru.aks.restoran.entities.Restaurant;
import ru.aks.restoran.repositories.RestoranRepo;
import ru.aks.restoran.service.RestoranServ;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class RestoranServImpl implements RestoranServ {
    private final RestoranRepo restoranRepo;

    public RestoranServImpl(RestoranRepo restoranRepo) {
        this.restoranRepo = restoranRepo;
    }

    @Override
    public SimpleResponse save(RestoranRequest restoranRequest) {

        Restaurant restaurant = new Restaurant();
        restaurant.setName(restoranRequest.getName());
        restaurant.setLocation(restoranRequest.getLocation());
        restaurant.setType(restoranRequest.getType());
        restaurant.setService(restoranRequest.getService());

        restoranRepo.save(restaurant);

        SimpleResponse response = new SimpleResponse();
        response.setHttpStatus(HttpStatus.CREATED);
        response.setMessage("Restaurant saved");
        return response;
    }

    @Override
    public RestoranResponse getById(Long id) {
        return null;
    }

    @Override
    public List<RestoranResponse> getAllRestaurants() {
        return restoranRepo.findAll().stream()
                .map(this::mapToRestaurantResp).collect(Collectors.toList());
    }

    private RestoranResponse mapToRestaurantResp(Restaurant restaurant) {
        RestoranResponse restoranResponse = new RestoranResponse();
        restoranResponse.setId(restaurant.getId());
        restoranResponse.setLocation(restaurant.getLocation());
        restoranResponse.setName(restaurant.getName());
        restoranResponse.setType(restaurant.getType());
        restoranResponse.setService(restaurant.getService());
        // restoranResponse.setNumberOfEmployees();
        return restoranResponse;
        }

    @Override
    public SimpleResponse updateById(Long id, RestoranResponse restoranResponse) {
        return null;
    }

    @Override
    public SimpleResponse deleteById(Long id) {
        return null;
    }
}
