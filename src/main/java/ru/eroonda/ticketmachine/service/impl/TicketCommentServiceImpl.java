package ru.eroonda.ticketmachine.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.eroonda.ticketmachine.entity.TicketComment;
import ru.eroonda.ticketmachine.repository.TicketCommentRepository;
import ru.eroonda.ticketmachine.service.TicketCommentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketCommentServiceImpl implements TicketCommentService {
    @Autowired
    private TicketCommentRepository ticketCommentRepository;

    @Override
    @Transactional
    public List<TicketComment> getCommentsById(int ticketId){

        List<TicketComment> comments = new ArrayList<>();

        Optional<List<TicketComment>> commentsListFromRepo =
                ticketCommentRepository.findByTicket_Id(ticketId);

        if(commentsListFromRepo.isPresent()){
            comments = commentsListFromRepo.get();
        }

        return comments;
    }
}
