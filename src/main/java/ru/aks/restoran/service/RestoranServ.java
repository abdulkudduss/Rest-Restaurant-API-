package ru.aks.restoran.service;

import ru.aks.restoran.dto.RestoranDto.RestoranRequest;
import ru.aks.restoran.dto.RestoranDto.RestoranResponse;
import ru.aks.restoran.dto.SimpleResponse;

import java.util.List;

public interface RestoranServ {
    SimpleResponse save(RestoranRequest restoranRequestd); // для проверки прав доступа
    RestoranResponse getById(Long id);
    List<RestoranResponse> getAllRestaurants();
    SimpleResponse updateById (Long id, RestoranResponse restoranResponse);
    SimpleResponse deleteById(Long id);
}
