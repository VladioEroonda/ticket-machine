package ru.eroonda.ticketmachine.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class EmailValidatorDto {

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")
    private String email;

    public EmailValidatorDto() {
    }

    public EmailValidatorDto(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
