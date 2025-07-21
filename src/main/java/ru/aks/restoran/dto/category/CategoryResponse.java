package ru.aks.restoran.dto.category;

import ru.aks.restoran.dto.subCategory.SubCategoryResponse;

import java.util.ArrayList;
import java.util.List;

public class CategoryResponse {
    Long id;
    String name;
    List<SubCategoryResponse> subCategoryResponses = new ArrayList<>();

    public CategoryResponse(Long id, String name, List<SubCategoryResponse> subCategoryResponses) {
        this.id = id;
        this.name = name;
        this.subCategoryResponses = subCategoryResponses;
    }

    public CategoryResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryResponse() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SubCategoryResponse> getSubCategoryResponses() {
        return subCategoryResponses;
    }

    public void setSubCategoryResponses(List<SubCategoryResponse> subCategoryResponses) {
        this.subCategoryResponses = subCategoryResponses;
    }
}
