package ru.aks.restoran.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aks.restoran.entities.Restaurant;
@Repository
public interface RestoranRepo extends JpaRepository<Restaurant,Long> {


}
