package itmo.blps.mommy.service;

import itmo.blps.mommy.entity.Purchase;
import itmo.blps.mommy.entity.User;
import itmo.blps.mommy.entity.UserPurchase;
import itmo.blps.mommy.repository.PurchaseRepository;
import itmo.blps.mommy.repository.UserPurchaseRepository;
import itmo.blps.mommy.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static itmo.blps.mommy.entity.PurchaseStatus.WAIT_PAYMENT;

@Service
@AllArgsConstructor
public class PurchaseCountService {
    private final UserRepository userRepository;
    private final PurchaseRepository purchaseRepository;
    private final UserPurchaseRepository userPurchaseRepository;
    private final EmailService emailService;

    public void countPurchasedProducts(UserPurchase db) throws Exception {
        Purchase purchase = purchaseRepository.findById(db.getId().getPurchaseId()).orElseThrow(() -> new Exception("Выкупа с существующим айди не найдено"));

        purchase.setCurCount(purchase.getCurCount() + db.getProductsCount());

        if (purchase.getCurCount() >= purchase.getMinCount()) {
            List<UserPurchase> userList = userPurchaseRepository.findAllById_PurchaseId(db.getId().getPurchaseId());
            for (UserPurchase u : userList) {
                if (!userRepository.existsById(u.getId().getUserId())) continue;
                User user = userRepository.getById(u.getId().getUserId());
                purchase.setPurchaseStatus(WAIT_PAYMENT);
                String message = generateMessage(purchase);
                emailService.send(user.getEmail(), "Выкуп товара", message);
            }
        }
        purchaseRepository.save(purchase);
    }

    private String generateMessage(Purchase purchase) {
        return "Пора начинать выкупать товар " + purchase.getProduct().getName() + "!";
    }
}
