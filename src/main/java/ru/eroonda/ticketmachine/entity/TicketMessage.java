package ru.eroonda.ticketmachine.entity;

import javax.persistence.*;

@Entity
@Table(name = "ticket_message")
public class TicketMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_message_id")
    private int id;
    @Column(name = "ticket_message_text")
    private String messageText;
    //uno-directional onetoone (with ticket.class) поэтому не буду хранить тикет (мб изменится при реализации поиска)

    public TicketMessage() {
    }

    public TicketMessage(String messageText) {
        this.messageText = messageText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
}
