package ru.aks.restoran.dto.user;

import ru.aks.restoran.enums.Role;
import jakarta.validation.constraints.*;
import ru.aks.restoran.validation.annotation.NoAdminRole;
import ru.aks.restoran.validation.annotation.ValidAge;
import ru.aks.restoran.validation.annotation.ValidExperience;

import java.time.LocalDate;

@ValidExperience
@ValidAge
public class UserRequest {
    Long restoranId;
    @NotBlank(message = "Имя обязательно")
    private String firstName;

    @NotBlank(message = "Фамилия обязательна")
    private String lastName;

    @NotBlank(message = "Email обязателен")
    @Email(message = "Некорректный формат email")
    private String email;

    @NotBlank(message = "Пароль обязателен")
    @Size(min = 4, message = "Пароль должен быть минимум 4 символа")
    private String password;

    @NotBlank(message = "Телефон обязателен")
    @Pattern(regexp = "\\+996\\d{9}", message = "Телефон должен быть в формате +996XXXXXXXXX")
    private String phone;
    @NoAdminRole //  запрет на выбор админа в заявке
    @NotNull(message = "Роль обязательна")
    private Role role;

    @Min(value = 0, message = "Опыт не может быть отрицательным")
    private int experience;

    @Past(message = "Дата рождения должна быть в прошлом")
    @NotNull(message = "Дата рождения обязательна")
    private LocalDate birthDate;

    public Long getRestoranId() {
        return restoranId;
    }

    public void setRestoranId(Long restoranId) {
        this.restoranId = restoranId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
