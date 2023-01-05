package by.bratchykau.receipt.controller;

import by.bratchykau.receipt.service.CheckService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckController {
    private final CheckService checkService;

    public CheckController(CheckService checkService) {
        this.checkService = checkService;
    }

    @GetMapping("/check")
    public String getCheck(@RequestParam("itemIdAndQuantity") String[] itemIdsAndQuantities,
                           @RequestParam(value = "discountCard", required = false) String discountCard) {
        return checkService.getCheck(itemIdsAndQuantities, discountCard);
    }
}
