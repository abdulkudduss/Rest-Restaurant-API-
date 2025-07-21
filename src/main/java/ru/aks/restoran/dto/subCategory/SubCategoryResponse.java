package ru.aks.restoran.dto.subCategory;

public class SubCategoryResponse {
    Long id;
    String name;

    public SubCategoryResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public SubCategoryResponse() {
    }
}
