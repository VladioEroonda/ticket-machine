package ru.eroonda.ticketmachine.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class TicketDto {

    @NotBlank
    @Size(min = 5, max = 150, message = "size from 5 to 150")
    private String ticketSubject;
    @NotBlank
    @Size(min = 10, max = 4000, message = "size from 10 to 4000")
    private String ticketMessage;

    public String getTicketSubject() {
        return ticketSubject;
    }

    public void setTicketSubject(String ticketSubject) {
        this.ticketSubject = ticketSubject;
    }

    public String getTicketMessage() {
        return ticketMessage;
    }

    public void setTicketMessage(String ticketMessage) {
        this.ticketMessage = ticketMessage;
    }
}
