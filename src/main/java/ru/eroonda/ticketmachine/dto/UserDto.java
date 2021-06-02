package ru.eroonda.ticketmachine.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDto {

    @NotBlank
    @Size(min = 2, max = 40, message = "Name must be between 2-40 symbols")
    private String name;
    @NotBlank
    @Size(min = 2, max = 40, message = "Surname must be between 2-40 symbols")
    private String surname;
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")
    private String email;
    @NotBlank
    @Size(min = 10, max = 25, message = "10 to 25")
    private String password;
    @NotBlank
    @Size(min = 10, max = 25, message = "10 to 25")
    private String passwordConfirm;
    @NotBlank
    @Pattern(regexp = "8\\(9\\d{2}\\)\\d{3}\\-\\d{2}\\-\\d{2}") //регулярка вида: 8(987)123-45-67
    private String phoneNumber;

    public UserDto(String name, String surname, String email, String password, String passwordConfirm, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.phoneNumber = phoneNumber;
    }

    public UserDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
