package by.bratchykau.receipt.utils;


import by.bratchykau.receipt.model.Product;
import by.bratchykau.receipt.model.Receipt;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ReceiptDecorator {
    private final Receipt receipt;

    public ReceiptDecorator(Receipt receipt) {
        this.receipt = receipt;
    }


    public void outputToFile(String fileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write("Receipt:");
            writer.newLine();
            for (Product product : receipt.getProducts()) {
                if (isDiscount(product)) {
                    writer.write(product.getName() + " x" + product.getQuantity() + " $" + convert(product.getPrice())
                            + " = $" + convert((product.getPrice() * product.getQuantity() * 90) / 100) + " with discount");
                    writer.newLine();
                } else {
                    writer.write(product.getName() + " x" + product.getQuantity() + " $" + convert(product.getPrice())
                            + " = $" + convert(product.getPrice() * product.getQuantity()));
                    writer.newLine();

                }
            }
            writer.write("Total: $" + convert(receipt.getTotalAmount()));
            writer.newLine();
            if (receipt.getDiscountCard() != null) {
                writer.write("Discount card: " + receipt.getDiscountCard().getNumber() + " percent " + receipt.getDiscountCard().getDiscountPercentage() + "%");
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isDiscount(Product product) {
        return product.getQuantity() > 5;
    }

    private static String convert(int value) {
        return String.format("%d.%02d", value/100, value % 100);
    }

}
