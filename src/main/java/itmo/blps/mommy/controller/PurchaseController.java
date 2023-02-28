package itmo.blps.mommy.controller;

import itmo.blps.mommy.dto.PurchaseRequestDTO;
import itmo.blps.mommy.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/purchases")
    public ResponseEntity<?> getPurchases(
            @RequestParam(value = "name", required = false, defaultValue = "") String name,
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "per_page", required = false, defaultValue = "20") Integer perPage
    ) {
        return ResponseEntity.ok(purchaseService.suggestPurchases(name, page, perPage));
    }

    @PostMapping("/admin/purchase/create")
    public ResponseEntity<?> createPurchase(@RequestBody PurchaseRequestDTO purchaseRequestDTO) {
        return ResponseEntity.ok(purchaseService.createPurchase(purchaseRequestDTO));
    }

    @DeleteMapping("/admin/purchase/{id}")
    public ResponseEntity<?> deletePurchase(@PathVariable int id) {
        purchaseService.deletePurchase(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/purchase/{id}")
    public ResponseEntity<?> getPurchase(@PathVariable int id) {
        return ResponseEntity.ok(purchaseService.getPurchase(id));
    }

}
