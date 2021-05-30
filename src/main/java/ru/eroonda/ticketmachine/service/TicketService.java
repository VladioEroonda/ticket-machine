package ru.eroonda.ticketmachine.service;


import ru.eroonda.ticketmachine.entity.Ticket;

public interface TicketService {
    public Ticket getTicketById(int ticketId);
}
