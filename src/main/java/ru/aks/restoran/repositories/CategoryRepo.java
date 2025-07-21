package ru.aks.restoran.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aks.restoran.entities.Category;
@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {
}
