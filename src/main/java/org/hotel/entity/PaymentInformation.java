package org.hotel.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "payment_information")
public class PaymentInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    private String cardNumber;
    private int cvv;

    public PaymentInformation() {
    }

    public Long getId() {
        return id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    @Override
    public String toString() {
        return "Builder{" +
                "id=" + getId() +
                ", cardNumber='" + cardNumber + '\'' +
                ", cvv=" + cvv +
                '}';
    }
}
