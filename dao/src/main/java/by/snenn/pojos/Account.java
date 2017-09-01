package by.snenn.pojos;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@ToString (exclude = {"creditCards", "user"})
@EqualsAndHashCode(exclude = {"creditCards", "user"})
@Entity
@Table(name = "ACCOUNT")
public class Account implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private Date date;
    @Column
    private int balance;
    @JsonIgnore
    @JoinColumn
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "account", orphanRemoval = true)
    private List<CreditCard> creditCards = new ArrayList<>();

    public Account(int balance, User user, List<CreditCard> creditCards, Date date) {
        this.balance = balance;
        this.user = user;
        this.creditCards = creditCards;
        this.date = date;

    }

    public Account() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CreditCard> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(List<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
