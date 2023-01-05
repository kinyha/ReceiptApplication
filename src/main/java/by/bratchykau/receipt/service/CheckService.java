package by.bratchykau.receipt.service;

import by.bratchykau.receipt.model.Receipt;

public interface CheckService {
    String getCheck(String[] itemIdsAndQuantities, String discountCard);
}
