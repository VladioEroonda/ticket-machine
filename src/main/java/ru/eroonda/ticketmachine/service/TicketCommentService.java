package ru.eroonda.ticketmachine.service;

import org.springframework.stereotype.Service;
import ru.eroonda.ticketmachine.entity.TicketComment;

import java.util.List;

public interface TicketCommentService {
    public List<TicketComment> getCommentsById(int ticketId);
}
