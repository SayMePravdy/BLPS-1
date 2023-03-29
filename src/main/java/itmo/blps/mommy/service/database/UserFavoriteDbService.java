package itmo.blps.mommy.service.database;

import itmo.blps.mommy.entity.UserFavoriteProduct;
import itmo.blps.mommy.exception.EntityNotFoundException;
import itmo.blps.mommy.repository.UserFavoriteProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserFavoriteDbService {
    private UserFavoriteProductRepository userFavoriteProductRepository;

    @Transactional
    public UserFavoriteProduct createFavoriteProduct(UserFavoriteProduct userFavoriteProduct) {
        return userFavoriteProductRepository.save(userFavoriteProduct);
    }

    @Transactional
    public void deleteFavoriteProduct(Integer userId, Integer productId) {
        userFavoriteProductRepository.deleteById_UserIdAndId_ProductId(userId, productId)
                .orElseThrow(() -> new EntityNotFoundException("Сущность избранной покупки не найдена"));
    }

    @Transactional(readOnly = true)
    public UserFavoriteProduct findUserIdAndProductId(Integer userId, Integer productId) {
        return userFavoriteProductRepository.findById_UserIdAndId_ProductId(userId, productId)
                .orElseThrow(() -> new EntityNotFoundException("В избранном не найдено такого продукта"));
    }

    @Transactional(readOnly = true)
    public Page<UserFavoriteProduct> findAllByUserId(Integer userId, Pageable pageable) {
        return userFavoriteProductRepository.findAllById_UserId(userId, pageable);

    }
}
