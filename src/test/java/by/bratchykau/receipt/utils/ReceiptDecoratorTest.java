package by.bratchykau.receipt.utils;

import by.bratchykau.receipt.model.DiscountCard;
import by.bratchykau.receipt.model.Product;
import by.bratchykau.receipt.model.Receipt;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReceiptDecoratorTest {

    @Test
    void CheckOutputToFile() {
        Receipt receipt = new Receipt(
                List.of(new Product(1, "Product 1", 100,1),
                        new Product(2, "Product 2", 200,2)),
                new DiscountCard("card-1", 10));
        ReceiptDecorator receiptDecorator = new ReceiptDecorator(receipt);
        receiptDecorator.outputToFile("src/test/resources/testOutput.txt");

        String expected = readTextFromFile("src/test/resources/Expected.txt");
        assertEquals(expected, readTextFromFile("src/test/resources/testOutput.txt"));
    }

    private String readTextFromFile(String filePath) {
        try {
            return Files.readString(Paths.get(filePath));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file " + filePath, e);
        }
    }


}