package itmo.blps.mommy.service;

import itmo.blps.mommy.entity.Purchase;
import itmo.blps.mommy.entity.User;
import itmo.blps.mommy.entity.UserPurchase;
import itmo.blps.mommy.repository.PurchaseRepository;
import itmo.blps.mommy.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static itmo.blps.mommy.entity.PurchaseStatus.WAIT_PAYMENT;

@Service
@AllArgsConstructor
public class PurchaseCountService {
    private final UserRepository userRepository;
    private final PurchaseRepository purchaseRepository;
    private final EmailService emailService;

    public void countPurchasedProducts(UserPurchase db) {
        Purchase purchase = purchaseRepository.getById(db.getId().getPurchaseId());
        purchase.setCurCount(purchase.getCurCount() + db.getProductsCount());

        if (purchase.getCurCount() >= purchase.getMinCount()) {
            purchase.setPurchaseStatus(WAIT_PAYMENT);
            User user = userRepository.getById(db.getId().getUserId());
            String message = generateMessage(purchase);
            emailService.send(user.getEmail(), "Выкуп товара", message);
        }
        purchaseRepository.save(purchase);
    }

    private String generateMessage(Purchase purchase) {
        return "Пора начинать выкупать товар " + purchase.getProduct().getName() + "!";
    }
}
