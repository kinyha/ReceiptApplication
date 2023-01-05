package by.bratchykau.receipt.model;

import java.util.List;

public class Receipt {
    private List<Product> products;
    private int totalAmount;
    private DiscountCard discountCard;

    public Receipt(List<Product> products, int totalAmount, DiscountCard discountCard) {
        this.products = products;
        this.totalAmount = totalAmount;
        this.discountCard = discountCard;
    }

    public List<Product> getProducts() {
        return products;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public DiscountCard getDiscountCard() {
        return discountCard;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setDiscountCard(DiscountCard discountCard) {
        this.discountCard = discountCard;
    }

    @Override
    public String toString() {
        return createString();
    }

    private String createString() {
        StringBuilder sb = new StringBuilder();
        int amount = 0;
        sb.append("Receipt:");
        for (Product product:products) {
            if (product.getQuantity() <= 5) {
                sb.append("\n");
                sb.append(product.getName()).append(" x").append(product.getQuantity()).append(" $")
                        .append(convert(product.getPrice())).append(" = $")
                        .append(convert(product.getPrice() * product.getQuantity()));
                amount += product.getQuantity() * product.getPrice();
            } else {
                sb.append("\n");
                sb.append(product.getName()).append(" x").append(product.getQuantity()).append(" $")
                        .append(convert(product.getPrice())).append(" = $")
                        .append(convert((product.getPrice() * product.getQuantity() * 90) / 100))
                        .append(" with discount");
                amount += (product.getPrice() * product.getQuantity() * 90) / 100;
            }
        }
        sb.append("\n");
        if (discountCard != null) {
            sb.append("Total: $" + convert(totalAmount));
        } else {
            sb.append("Total: $" + convert(amount));
        }
        sb.append("\n");
        if (discountCard != null) {
            sb.append("Discount card: " + discountCard.getNumber() + " percent " + discountCard.getDiscountPercentage() + "%");
            sb.append("\n");
        }
        return sb.toString();
    }

    private static String convert(int value) {
        return String.format("%d.%02d", value/100, value % 100);
    }
}
