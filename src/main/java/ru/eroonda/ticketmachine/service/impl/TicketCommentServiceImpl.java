package ru.eroonda.ticketmachine.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.eroonda.ticketmachine.dto.CommentDto;
import ru.eroonda.ticketmachine.entity.TicketComment;
import ru.eroonda.ticketmachine.repository.TicketCommentRepository;
import ru.eroonda.ticketmachine.repository.TicketRepository;
import ru.eroonda.ticketmachine.repository.UserRepository;
import ru.eroonda.ticketmachine.service.TicketCommentService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketCommentServiceImpl implements TicketCommentService {
    @Autowired
    private TicketCommentRepository ticketCommentRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public List<TicketComment> getCommentsById(int ticketId){

        List<TicketComment> comments = new ArrayList<>();

        Optional<List<TicketComment>> commentsListFromRepo =
                ticketCommentRepository.findByTicket_Id(ticketId);

        if(commentsListFromRepo.isPresent()){
            comments = commentsListFromRepo.get();
        }

        return comments;
    }

    @Transactional
    @Override
    public void addNewComment(CommentDto commentDto,int ticketId, int userId) {
        System.out.println("IDDDDD: " + ticketId);
        System.out.println("TEXTTTT: " + commentDto.getCommentText());

        TicketComment comment = new TicketComment(
                LocalDateTime.now(),
                commentDto.getCommentText(),
                ticketRepository.getById(ticketId),
                userRepository.getById(userId)
        );

        ticketCommentRepository.save(comment);
    }
}
