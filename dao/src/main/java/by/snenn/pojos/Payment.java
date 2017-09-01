package by.snenn.pojos;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@ToString (exclude = {"creditCardPayment"})
@EqualsAndHashCode
@Entity
@Table(name = "PAYMENT")
public class Payment implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;
    @Column
    private int sum;
    @Column
    private int creditCardPayment;
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

    public int getCreditCardPayment() {
        return creditCardPayment;
    }

    public void setCreditCardPayment(int creditCardPayment) {
        this.creditCardPayment = creditCardPayment;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}