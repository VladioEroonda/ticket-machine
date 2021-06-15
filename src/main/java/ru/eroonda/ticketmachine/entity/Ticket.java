package ru.eroonda.ticketmachine.entity;

import ru.eroonda.ticketmachine.enums.TicketStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private int id;
    @Column(name = "ticket_creation_time")
    private LocalDateTime creationTime;
    @Column(name = "ticket_closing_time")
    private LocalDateTime closingTime;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    @JoinColumn(name = "ticket_client_id")
    private User client;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ticket_engineer_id")
    private User engineer;
    @Column(name = "ticket_status")
    @Enumerated(EnumType.ORDINAL)
    private TicketStatus status; 
    @Column(name = "ticket_subject")
    private String subject;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_message_id")
    private TicketMessage message;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ticket", fetch = FetchType.EAGER)
//    private List<TicketComment> comments;

    public Ticket() {
    }

    public Ticket(TicketMessage message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public LocalDateTime getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(LocalDateTime closingTime) {
        this.closingTime = closingTime;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public User getEngineer() {
        return engineer;
    }

    public void setEngineer(User engineer) {
        this.engineer = engineer;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public TicketMessage getMessage() {
        return message;
    }

    public void setMessage(TicketMessage message) {
        this.message = message;
    }

//    public List<TicketComment> getComments() {
//        return comments;
//    }
//
//    public void setComments(List<TicketComment> comments) {
//        this.comments = comments;
//    }
}
