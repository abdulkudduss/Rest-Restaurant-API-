package ru.aks.restoran.service;

import ru.aks.restoran.dto.SimpleResponse;
import ru.aks.restoran.dto.category.CategoryRequest;
import ru.aks.restoran.dto.category.CategoryResponse;

import java.util.List;

public interface CategoryServ {
    SimpleResponse save(CategoryRequest request);

   List< CategoryResponse> getCategories();
}
