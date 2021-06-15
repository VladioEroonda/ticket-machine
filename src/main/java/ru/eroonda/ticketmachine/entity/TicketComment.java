package ru.eroonda.ticketmachine.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ticket_comments")
public class TicketComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private int id;
    @Column(name = "comment_date")
    private LocalDateTime commentDate;
    @Column(name = "comment_message")
    private String messageText;
    @ManyToOne(fetch = FetchType.LAZY)// cascade = CascadeType.REFRESH?
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "comment_author_id")
    private User author;

    public TicketComment() {
    }

    public TicketComment(LocalDateTime commentDate, String messageText) {
        this.commentDate = commentDate;
        this.messageText = messageText;
    }

    public TicketComment(LocalDateTime commentDate, String messageText, Ticket ticket, User author) {
        this.commentDate = commentDate;
        this.messageText = messageText;
        this.ticket = ticket;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(LocalDateTime commentDate) {
        this.commentDate = commentDate;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

}
