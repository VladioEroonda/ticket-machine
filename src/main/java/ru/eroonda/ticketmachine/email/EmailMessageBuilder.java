package ru.eroonda.ticketmachine.email;

public class EmailMessageBuilder {
    private final static String VALIDATION_LINK = "http://localhost:8080/auth/registration_confirming?token=";//TODO: DON'T FORGET WHEN DEPLOY
    private final static String PASSWORD_RESETTING_LINK = "http://localhost:8080/auth/new_password?token=";

    public static String getValidationMessage(String token, String name, String surName){
        return "Hello, " + name + " " + surName + ". " +
                "Thank you for registering.\n" +
                "Please click on the below link to activate your account:\n " +
                "your link: " + VALIDATION_LINK + token + " \n " +
                "Link will expire in 15 minutes.";
    }

    public static String getPasswordResettingMessage(String token, String name, String surName){
        return "Hello, " + name + " " + surName + ". " +
                "Someone request resetting your password.\n" +
                "If that was not you - just ignore that email.\n " +
                "Otherwise open this link and change your password\n " +
                "your link: " + PASSWORD_RESETTING_LINK + token + " \n " +
                "Link will expire in 15 minutes.";
    }
}
