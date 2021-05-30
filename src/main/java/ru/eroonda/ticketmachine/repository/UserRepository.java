package ru.eroonda.ticketmachine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.eroonda.ticketmachine.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByEmail(String email);
    public User findByName(String name);
}
