package ru.aks.restoran.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.aks.restoran.enums.RestaurantType;


import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurants")

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restaurant_gen")
    @SequenceGenerator(name = "restaurant_gen", sequenceName = "restaurant_seq", allocationSize = 1, initialValue = 1)
    Long id;
    String name;
    String location;
    @Enumerated(EnumType.STRING)
    RestaurantType type;
    int numberOfEmployees = 0;
    int service;
    @OneToMany (mappedBy = "restaurant", cascade = CascadeType.REMOVE)
    List<User> users = new ArrayList<>();
    @OneToMany (mappedBy = "restaurant", cascade = CascadeType.REMOVE)
    List<MenuItem> menuItems = new ArrayList<>();

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public RestaurantType getType() {
        return type;
    }

    public void setType(RestaurantType type) {
        this.type = type;
    }

    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(int numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    public int getService() {
        return service;
    }

    public void setService(int service) {
        this.service = service;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }
}
