package by.bratchykau.receipt.utils;


import by.bratchykau.receipt.model.DiscountCard;
import by.bratchykau.receipt.model.Product;
import by.bratchykau.receipt.model.Receipt;

import java.util.ArrayList;
import java.util.List;

public class ReceiptBuilder {
    private final List<Product> products = new ArrayList<>();
    private int totalAmount;
    private DiscountCard discountCard;

    public void addProduct(Product product) {
        products.add(product);
        totalAmount += product.getPrice() * product.getQuantity();
    }

    public void setDiscountCard(DiscountCard discountCard) {
        this.discountCard = discountCard;
    }

    public Receipt build() {
        return new Receipt(products, discountCard);
    }
}
