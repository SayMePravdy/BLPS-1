package itmo.blps.mommy.controller;

import itmo.blps.mommy.dto.OrderPurchaseDto;
import itmo.blps.mommy.entity.UserPurchase;
import itmo.blps.mommy.service.OrderPurchaseService;
import itmo.blps.mommy.validator.ValidProduct;
import itmo.blps.mommy.validator.ValidUser;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/orderPurchase")
@AllArgsConstructor
public class OrderPurchaseController {
    private final OrderPurchaseService orderPurchaseService;

    @PostMapping("/create")
    public ResponseEntity<OrderPurchaseDto> addOrderPurchase(@RequestBody @Valid OrderPurchaseDto orderPurchaseDto) throws Exception {
        return ResponseEntity.ok(orderPurchaseService.addOrderPurchase(orderPurchaseDto));
    }

    @GetMapping("/{userId}/purchases/{purchaseId}")
    public ResponseEntity<OrderPurchaseDto> getOrderPurchase(@PathVariable(name = "userId") @Valid @ValidUser Integer userId,
                                                             @PathVariable(name = "purchaseId") @Valid @ValidProduct Integer purchaseId) {
        return ResponseEntity.ok(orderPurchaseService.getOrderPurchase(userId, purchaseId));
    }


    @DeleteMapping("/{userId}/purchases/{purchaseId}")
    public ResponseEntity<OrderPurchaseDto> deleteOrderPurchase(@PathVariable(name = "userId") @Valid @ValidUser Integer userId,
                                                                @PathVariable(name = "purchaseId") @Valid @ValidProduct Integer purchaseId) {
        return ResponseEntity.ok(orderPurchaseService.deleteOrderPurchase(userId, purchaseId));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<UserPurchase>> getPagedOrderPurchase(@PathVariable(name = "userId") @Valid @ValidUser Integer userId,
                                                                    @RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                                                    @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize) {
        return ResponseEntity.ok(orderPurchaseService.getPagedOrderPurchase(userId, page, pageSize).getContent());
    }
}
