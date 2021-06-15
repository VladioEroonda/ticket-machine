package ru.eroonda.ticketmachine.service;

import org.springframework.stereotype.Service;
import ru.eroonda.ticketmachine.dto.CommentDto;
import ru.eroonda.ticketmachine.entity.TicketComment;

import java.util.List;

public interface TicketCommentService {
    List<TicketComment> getCommentsById(int ticketId);
    void addNewComment(CommentDto commentDto,int ticketId, int userId);
}
