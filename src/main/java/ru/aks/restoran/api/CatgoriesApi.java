package ru.aks.restoran.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.aks.restoran.dto.SimpleResponse;
import ru.aks.restoran.dto.category.CategoryRequest;
import ru.aks.restoran.dto.category.CategoryResponse;
import ru.aks.restoran.dto.subCategory.SubCategoryRequest;
import ru.aks.restoran.service.CategoryServ;
import ru.aks.restoran.service.SubCategoryServ;

import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CatgoriesApi {
    private final CategoryServ categoryServ;
    private final SubCategoryServ subCategory;

    public CatgoriesApi(CategoryServ categoryServ, SubCategoryServ subCategory) {
        this.categoryServ = categoryServ;
        this.subCategory = subCategory;
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN') or hasRole('CHEF')")
    public SimpleResponse createCategory(@RequestBody CategoryRequest request) {
        return categoryServ.save(request);
    }

    @GetMapping()
    public List<CategoryResponse> getCategories() {
        return categoryServ.getCategories();
    }

    public SimpleResponse createSubCategory(SubCategoryRequest request){
        return subCategory.save(request);
    }
}
