package by.bratchykau.receipt.model;

import javax.persistence.*;

@Entity
@Table(name = "discount_card")
public class DiscountCard {
    @Id
    @Column(name = "number")
    private String number;

    @Basic
    @Column(name = "discount_percentage")
    private int discountPercentage;

    public DiscountCard() {

    }

    public DiscountCard(String number, int discountPercentage) {
        this.number = number;
        this.discountPercentage = discountPercentage;
    }

    public String getNumber() {
        return number;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}


