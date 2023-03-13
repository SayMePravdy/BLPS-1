package itmo.blps.mommy.mapper;

import itmo.blps.mommy.dto.UserFavoriteDto;
import itmo.blps.mommy.entity.UserFavoriteProduct;
import org.springframework.stereotype.Component;

@Component
public class UserFavoriteMapper {
    public UserFavoriteDto toDto(UserFavoriteProduct userFavoriteProduct) {
        return new UserFavoriteDto()
                .setProductId(userFavoriteProduct.getId().getProductId());
    }
}
