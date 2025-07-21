package ru.aks.restoran.service;

import ru.aks.restoran.dto.SimpleResponse;
import ru.aks.restoran.dto.subCategory.SubCategoryRequest;

public interface SubCategoryServ {
    SimpleResponse save(SubCategoryRequest request);
}
