package ru.eroonda.ticketmachine.dto;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class PasswordResetDto {

    @Transient
    private String email;
    @Transient
    private String token;
    @NotBlank
    @Size(min = 10, max = 25, message = "10 to 25")
    private String password;
    @NotBlank
    @Size(min = 10, max = 25, message = "10 to 25")
    private String passwordConfirmed;

    public PasswordResetDto() {
    }

    public PasswordResetDto(String email, String token) {
        this.email = email;
        this.token = token;
    }

    public PasswordResetDto(String email, String token, String password, String passwordConfirmed) {
        this.email = email;
        this.token = token;
        this.password = password;
        this.passwordConfirmed = passwordConfirmed;
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

    public String getPasswordConfirmed() {
        return passwordConfirmed;
    }

    public void setPasswordConfirmed(String passwordConfirmed) {
        this.passwordConfirmed = passwordConfirmed;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
