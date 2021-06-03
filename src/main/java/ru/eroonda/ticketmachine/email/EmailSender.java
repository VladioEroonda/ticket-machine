package ru.eroonda.ticketmachine.email;

public interface EmailSender {
    void send(String to,String emailSubject, String emailMessage);
}
