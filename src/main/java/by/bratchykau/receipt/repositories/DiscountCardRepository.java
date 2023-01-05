package by.bratchykau.receipt.repositories;

import by.bratchykau.receipt.model.DiscountCard;
import by.bratchykau.receipt.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DiscountCardRepository extends JpaRepository<DiscountCard, Integer> {
    DiscountCard findByNumber(String number);
}