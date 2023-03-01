package itmo.blps.mommy.repository;

import itmo.blps.mommy.entity.UserPurchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPurchasedRepository extends JpaRepository<UserPurchase, Integer> {
    Optional<UserPurchase> findById_UserIdAndId_PurchaseId(Integer userId, Integer purchaseId);

    Optional<UserPurchase> deleteById_UserIdAndId_PurchaseId(Integer userId, Integer purchaseId);

    Page<UserPurchase> findAllById_UserId(Integer userId, Pageable pageable);
}
