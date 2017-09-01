package by.snenn.pojos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@ToString(exclude = {"cardSender","cardRecipient"})
@EqualsAndHashCode
@Entity
@Table(name = "TRANSFER")
public class Transfer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;
    @Column
    private int sum;
    @Column
    private int cardSender;
    @Column
    private int cardRecipient;
    @Column
    private Date data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getCardSender() {
        return cardSender;
    }

    public void setCardSender(int cardSender) {
        this.cardSender = cardSender;
    }

    public int getCardRecipient() {
        return cardRecipient;
    }

    public void setCardRecipient(int cardRecipient) {
        this.cardRecipient = cardRecipient;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}