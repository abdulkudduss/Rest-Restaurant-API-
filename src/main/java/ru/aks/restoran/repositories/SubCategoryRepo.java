package ru.aks.restoran.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aks.restoran.entities.SubCategory;

@Repository
public interface SubCategoryRepo extends JpaRepository<SubCategory,Long> {
}
