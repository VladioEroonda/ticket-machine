package ru.eroonda.ticketmachine.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "user_info")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private int id;
    @NotBlank
    @Size(min = 2, max = 40, message = "Name must be between 2-40 symbols")
    @Column(name = "user_name")
    private String name;
    @NotBlank
    @Size(min = 2, max = 40, message = "Surname must be between 2-40 symbols")
    @Column(name = "user_surname")
    private String surname;
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")
    @Column(name="user_email")
    private String email;
    @NotBlank
    @Size(min = 10, max = 25, message = "10 to 25")
    @Column(name="user_password")
    private String password;
    @Transient
    @NotBlank
    @Size(min = 10, max = 25, message = "10 to 25")
    private String passwordConfirm;
    @Column(name="user_enabled")
    private boolean isEnabled;
    @NotBlank
    @Column(name = "user_phone")
    @Pattern(regexp = "8\\(9\\d{2}\\)\\d{3}\\-\\d{2}\\-\\d{2}") //регулярка вида: 8(987)123-45-67
    private String phoneNumber;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client", fetch = FetchType.LAZY)//FetchType.LAZY дефолт
    private List<Ticket> ticketListAsUser;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "engineer",fetch = FetchType.LAZY)
    private List<Ticket> ticketListAsEngineer;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_info_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User() {
    }

    public User(String name, String surname, String email, String password, boolean isEnabled, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.isEnabled = isEnabled;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Ticket> getTicketListAsUser() {
        return ticketListAsUser;
    }

    public void setTicketListAsUser(List<Ticket> ticketListAsUser) {
        this.ticketListAsUser = ticketListAsUser;
    }

    public List<Ticket> getTicketListAsEngineer() {
        return ticketListAsEngineer;
    }

    public void setTicketListAsEngineer(List<Ticket> ticketListAsEngineer) {
        this.ticketListAsEngineer = ticketListAsEngineer;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isEnabled=" + isEnabled +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", role ='" + roles + '\'' +
                '}';
    }
}
