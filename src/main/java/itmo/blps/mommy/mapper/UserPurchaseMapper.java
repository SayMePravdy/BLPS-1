package itmo.blps.mommy.mapper;

import itmo.blps.mommy.dto.OrderPurchaseDto;
import itmo.blps.mommy.dto.UserFavoriteDto;
import itmo.blps.mommy.entity.UserPurchase;
import org.springframework.stereotype.Component;

@Component
public class UserPurchaseMapper {
    public OrderPurchaseDto toDto(UserPurchase userPurchase) {
        return new OrderPurchaseDto()
                .setUserId(userPurchase.getId().getUserId())
                .setPurchaseId(userPurchase.getId().getPurchaseId())
                .setCountOfProducts(userPurchase.getProductsCount());
    }
}
