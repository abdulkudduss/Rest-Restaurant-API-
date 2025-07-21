package ru.aks.restoran.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.aks.restoran.dto.SimpleResponse;
import ru.aks.restoran.dto.category.CategoryRequest;
import ru.aks.restoran.dto.category.CategoryResponse;
import ru.aks.restoran.entities.Category;
import ru.aks.restoran.repositories.CategoryRepo;
import ru.aks.restoran.service.CategoryServ;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServImpl implements CategoryServ {
   private final CategoryRepo categoryRepo;

    public CategoryServImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public SimpleResponse save(CategoryRequest request) {
        Category category = new Category();
        category.setName(request.getName());
        categoryRepo.save(category);
        return new SimpleResponse (HttpStatus.CREATED,"Succes!");
    }

    @Override
    public List< CategoryResponse> getCategories() {
        return categoryRepo.findAll().stream().map(this::mapToCategory).collect(Collectors.toList());
    }
    private CategoryResponse mapToCategory(Category category){
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setName(category.getName());
        categoryResponse.setId(category.getId());
        return categoryResponse;
    }
}
