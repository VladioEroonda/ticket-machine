package ru.eroonda.ticketmachine.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reg_confirmation_token")
public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private int id;
    @Column(name = "token")
    private String token;
    @Column(name = "token_created")
    private LocalDateTime createdAt;
    @Column(name = "token_expired")
    private LocalDateTime expiredAt;
    @Column(name = "token_confirmed")
    private LocalDateTime confirmedAt;
    @ManyToOne//(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "token_user_id")
    private User user;

    public ConfirmationToken() {
    }

    public ConfirmationToken(String token,
                             LocalDateTime createdAt,
                             LocalDateTime expiredAt,
                             User user) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
        this.user=user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(LocalDateTime expiredAt) {
        this.expiredAt = expiredAt;
    }

    public LocalDateTime getConfirmedAt() {
        return confirmedAt;
    }

    public void setConfirmedAt(LocalDateTime confirmedAt) {
        this.confirmedAt = confirmedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
