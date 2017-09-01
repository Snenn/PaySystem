package by.snenn.pojos;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;


@Data
@ToString (exclude = "creditCards")
@EqualsAndHashCode(exclude = "creditCards")
@Entity
@Table(name = "CREDIT_CARD_STATUS")
public class CreditCardStatus implements Serializable {

    @Id
    @Column
    private int id;
    @Column
    private String status;


    public CreditCardStatus(int id, String status) {
        this.id = id;
        this.status = status;
    }

    public CreditCardStatus() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
