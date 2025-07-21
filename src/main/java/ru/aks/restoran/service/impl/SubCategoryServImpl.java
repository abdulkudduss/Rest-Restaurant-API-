package ru.aks.restoran.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.aks.restoran.dto.SimpleResponse;
import ru.aks.restoran.dto.subCategory.SubCategoryRequest;
import ru.aks.restoran.repositories.SubCategoryRepo;
import ru.aks.restoran.service.SubCategoryServ;
@Service
public class SubCategoryServImpl implements SubCategoryServ {
    private final SubCategoryRepo subCategoryRepo;

    public SubCategoryServImpl(SubCategoryRepo subCategoryRepo) {
        this.subCategoryRepo = subCategoryRepo;
    }

    @Override
    public SimpleResponse save(SubCategoryRequest request) {

       // subCategoryRepo.save()
        return new SimpleResponse(HttpStatus.CREATED,"Success!");
    }
}
