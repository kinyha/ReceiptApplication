package by.bratchykau.receipt.model;


import by.bratchykau.receipt.exceptions.NonpositiveArgumentException;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private int price;

    @Transient
    private int quantity;

    public Product() {

    }

    public Product(int id, String name, int price) {
        if (price <= 0) {
            throw new NonpositiveArgumentException("Wrong argument for value: ", price);
        }
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name is empty");
        }
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Product(int id, String name, int price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}


