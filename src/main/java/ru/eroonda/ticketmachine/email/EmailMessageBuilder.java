package ru.eroonda.ticketmachine.email;

public class EmailMessageBuilder {
    private final static String VALIDATION_LINK = "http://localhost:8080/auth/confirm?token=";//TODO: DON'T FORGET WHEN DEPLOY
    public static String getValidationMessage(String token, String name, String surName){
        return "Hey dear " + name + " " + surName + ". <br>\n " +
                "Thank you for registering.\n" +
                "Please click on the below link to activate your account: <br>  +\n " +
                "<a href=\" " + VALIDATION_LINK + token + "\">Activate Now</a><br>\n " +
                "Link will expire in 15 minutes.";
    }
}
