package itmo.blps.mommy.controller;

import itmo.blps.mommy.dto.PurchaseRequestDTO;
import itmo.blps.mommy.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/user/purchases")
    public ResponseEntity<?> getPurchases(
            @RequestParam("name") String name,
            @RequestParam("page") Integer page,
            @RequestParam("per_page") Integer perPage
    ) {
        return ResponseEntity.ok(purchaseService.suggestPurchases(name, page, perPage));
    }

    @GetMapping("/admin/purchase/create")
    public ResponseEntity<?> createPurchase(@RequestBody PurchaseRequestDTO purchaseRequestDTO) {
        return ResponseEntity.ok(purchaseService.createPurchase(purchaseRequestDTO));
    }
}
