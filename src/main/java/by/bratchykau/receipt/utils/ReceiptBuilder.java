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
        int totalAmount = 0;

        // Calculate total amount of the receipt
        for (Product product : products) {
            if (product.getQuantity() > 5) {         // Apply 10% discount on items with a quantity of more than five
                totalAmount += (product.getPrice() * product.getQuantity() * 0.9);
            } else {
                totalAmount += product.getPrice() * product.getQuantity();
            }
        }

//         Apply discount on the presented card (if there is one)
            if (discountCard != null) {
                totalAmount = (totalAmount  * (100 - discountCard.getDiscountPercentage())) / 100;
            }
        return new Receipt(products, totalAmount, discountCard);
    }
}
