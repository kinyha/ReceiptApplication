package by.bratchykau.receipt.service;

import by.bratchykau.receipt.model.DiscountCard;
import by.bratchykau.receipt.model.Product;
import by.bratchykau.receipt.model.Receipt;
import by.bratchykau.receipt.repositories.DiscountCardRepository;
import by.bratchykau.receipt.repositories.ProductRepository;
import by.bratchykau.receipt.utils.ReceiptBuilder;
import by.bratchykau.receipt.utils.ReceiptDecorator;
import org.springframework.stereotype.Service;

@Service
public class CheckServiceImpl implements CheckService {
    ProductRepository productRepository;
    DiscountCardRepository discountCardRepository;

    public CheckServiceImpl(ProductRepository productRepository, DiscountCardRepository discountCardRepository) {
        this.productRepository = productRepository;
        this.discountCardRepository = discountCardRepository;
    }

    @Override
    public String getCheck(String[] itemIdsAndQuantities, String discountCard) {

        ReceiptBuilder builder = new ReceiptBuilder();
        DiscountCard card = null;
        for (String line : itemIdsAndQuantities) {
            // this is a product
            String[] parts = line.split("-");

            int id = Integer.parseInt(parts[0]);
            int quantity = Integer.parseInt(parts[1]);
            Product product = new Product();
            try {
                product = productRepository.findById(id);
            } catch (IndexOutOfBoundsException e) {
                System.err.println(e.getMessage());
                throw new IndexOutOfBoundsException();
            }
            product.setQuantity(quantity);
            builder.addProduct(product);
        }

        if (discountCard != null) {
            if (discountCard.startsWith("card")) {
                card = discountCardRepository.findByNumber(discountCard);
            }
        }
        builder.setDiscountCard(card);
        Receipt receipt = builder.build();

        // output receipt to file
        ReceiptDecorator decorator = new ReceiptDecorator(receipt);
        decorator.outputToFile("src/main/resources/output.txt");
        return receipt.toString();

    }
}
