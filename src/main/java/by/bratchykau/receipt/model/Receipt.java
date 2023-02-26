package by.bratchykau.receipt.model;

import java.util.List;

public class Receipt {
    private List<Product> products;
    private int totalAmount;
    private DiscountCard discountCard;

    public Receipt(List<Product> products, DiscountCard discountCard) {
        this.products = products;
        this.discountCard = discountCard;
        this.totalAmount = findTotalAmount();
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

    public int findTotalAmount() {
        int amount = 0;
        for (Product product : products) {
            int price = product.getPrice() * product.getQuantity();
            if (product.getQuantity() > 5) {
                price = (int) (price * 0.9);
            }
            amount += price;
        }
        if (discountCard != null) {
            amount = (amount * (100 - discountCard.getDiscountPercentage())) / 100;
        }
        return amount;
    }

    @Override
    public String toString() {
        return createString();
    }

    private String createString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Receipt:\n");

        for (Product product : products) {
            int price = product.getPrice() * product.getQuantity();
            if (product.getQuantity() > 5) {
                price = (int) (price * 0.9);
                sb.append(product.getName()).append(" x").append(product.getQuantity()).append(" $")
                        .append(convert(product.getPrice())).append(" = $").append(convert(price))
                        .append(" with discount\n");
            } else {
                sb.append(product.getName()).append(" x").append(product.getQuantity()).append(" $")
                        .append(convert(product.getPrice())).append(" = $").append(convert(price)).append("\n");
            }
        }

        sb.append("Total: $").append(convert(totalAmount)).append("\n");

        if (discountCard != null) {
            sb.append("Discount card: ").append(discountCard.getNumber())
                    .append(" percent ").append(discountCard.getDiscountPercentage()).append("%\n");
        }

        return sb.toString();
    }


    private static String convert(int value) {
        return String.format("%d.%02d", value / 100, value % 100);
    }
}
