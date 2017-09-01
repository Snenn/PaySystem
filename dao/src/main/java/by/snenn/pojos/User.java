package by.snenn.pojos;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "USER")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    @Size(min = 1, max = 20, message = "Your name must be between 1 and 20 characters long.")
    @Pattern(regexp = "[а-яА-ЯёЁa-zA-Z]+")
    private String surname;
    @Column
    @Size(min = 1, max = 20, message = "Your name must be between 1 and 20 characters long.")
    @Pattern(regexp = "[а-яА-ЯёЁa-zA-Z]+")
    private String name;
    @Column
    @Size(min = 1, max = 20, message = "Your name must be between 1 and 20 characters long.")
    @Pattern(regexp = "[а-яА-ЯёЁa-zA-Z0-9-_.]+")
    private String login;
    @Column
    @Size(min = 1, max = 20, message = "Your name must be between 1 and 20 characters long.")
    private String password;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Account> accounts = new ArrayList<>();
    @ManyToOne
    @JoinColumn
    private UserRole userRole;

    public User(String surname, String name, String login, String password, List<Account> accounts, UserRole userRole) {
        this.surname = surname;
        this.name = name;
        this.login = login;
        this.password = password;
        this.accounts = accounts;
        this.userRole = userRole;
    }

    public User() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
