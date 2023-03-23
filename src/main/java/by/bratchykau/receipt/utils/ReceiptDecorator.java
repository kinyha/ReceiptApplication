package by.bratchykau.receipt.utils;


import by.bratchykau.receipt.model.Product;
import by.bratchykau.receipt.model.Receipt;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
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

    public void outputToFilePDF(String fileName) {
        try {
            PdfReader reader = new PdfReader("src/main/resources/Clevertec_Template.pdf");
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(fileName));

            // Add content to the PDF
            PdfContentByte canvas = stamper.getOverContent(1);
            BaseFont font = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            canvas.beginText();
            canvas.setFontAndSize(font, 12);
            canvas.showTextAligned(Element.ALIGN_LEFT, "Receipt:", 50, 700, 0);
            int y = 680;
            for (Product product : receipt.getProducts()) {
                String line;
                if (isDiscount(product)) {
                    line = product.getName() + " x" + product.getQuantity() + " $" + convert(product.getPrice()) + " = $" + convert((product.getPrice() * product.getQuantity() * 90) / 100) + " with discount";
                } else {
                    line = product.getName() + " x" + product.getQuantity() + " $" + convert(product.getPrice()) + " = $" + convert(product.getPrice() * product.getQuantity());
                }
                canvas.showTextAligned(Element.ALIGN_LEFT, line, 50, y, 0);
                y -= 20;
            }
            canvas.showTextAligned(Element.ALIGN_LEFT, "Total: $" + convert(receipt.getTotalAmount()), 50, y, 0);
            y -= 20;
            if (receipt.getDiscountCard() != null) {
                canvas.showTextAligned(Element.ALIGN_LEFT, "Discount card: " + receipt.getDiscountCard().getNumber() + " percent " + receipt.getDiscountCard().getDiscountPercentage() + "%", 50, y, 0);
            }

            stamper.close();
            reader.close();
        } catch (IOException | DocumentException e) {
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
