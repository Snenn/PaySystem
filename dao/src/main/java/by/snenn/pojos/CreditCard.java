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
@ToString (exclude = {"account","payments","transfersSender","cardRecipient"})
@EqualsAndHashCode(exclude = {"creditCardStatus", "account","payments","transfersSender","cardRecipient"})
@Entity
@Table(name = "CREDIT_CARD")
public class CreditCard implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private Date date;
    @Column
    private int creditCardStatus;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Account account;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "creditCardPayment", orphanRemoval = true)
    private List<Payment> payments = new ArrayList<>();
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "cardSender", orphanRemoval = true)
    private List<Transfer> transfersSender = new ArrayList<>();
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "cardRecipient", orphanRemoval = true)
    private List<Transfer> transfersRecipient = new ArrayList<>();
    @Column
    private int viewNumber;



    public CreditCard(int creditCardStatus, Account account, int viewNumber, Date date) {
        this.creditCardStatus = creditCardStatus;
        this.account = account;
        this.viewNumber = viewNumber;
        this.date = date;
    }

    public CreditCard() {

    }
    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public List<Transfer> getTransfersSender() {
        return transfersSender;
    }

    public void setTransfersSender(List<Transfer> transfersSender) {
        this.transfersSender = transfersSender;
    }

    public List<Transfer> getTransfersRecipient() {
        return transfersRecipient;
    }

    public void setTransfersRecipient(List<Transfer> transfersRecipient) {
        this.transfersRecipient = transfersRecipient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCreditCardStatus() {
        return creditCardStatus;
    }

    public void setCreditCardStatus(int creditCardStatus) {
        this.creditCardStatus = creditCardStatus;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getViewNumber() {
        return viewNumber;
    }

    public void setViewNumber(int viewNumber) {
        this.viewNumber = viewNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
