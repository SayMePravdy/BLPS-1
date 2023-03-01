package itmo.blps.mommy.service;

import itmo.blps.mommy.dto.UserFavoriteDto;
import itmo.blps.mommy.entity.UserFavoriteProduct;
import itmo.blps.mommy.exception.EntityNotFoundException;
import itmo.blps.mommy.mapper.UserFavoriteMapper;
import itmo.blps.mommy.repository.UserFavoriteProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
public class FavoriteProductService {
    private UserFavoriteProductRepository userFavoriteProductRepository;
    private UserFavoriteMapper userFavoriteMapper;

    public UserFavoriteDto addFavoriteProduct(Integer userId, Integer productId) {
        UserFavoriteProduct userFavoriteProduct = new UserFavoriteProduct(userId, productId);
        userFavoriteProduct.setDateCreated(Instant.now());
        return userFavoriteMapper.toDto(userFavoriteProductRepository.save(userFavoriteProduct));
    }

    public UserFavoriteDto getFavoriteProduct(Integer userId, Integer productId) {
        return userFavoriteMapper.toDto(userFavoriteProductRepository.findById_UserIdAndId_ProductId(userId, productId)
                .orElseThrow(() -> new EntityNotFoundException("Сущность избранной покупки не найдена")));
    }

    public UserFavoriteDto deleteFavoriteProduct(Integer userId, Integer productId) {
        return userFavoriteMapper.toDto(userFavoriteProductRepository.deleteById_UserIdAndId_ProductId(userId, productId)
                .orElseThrow(() -> new EntityNotFoundException("Сущность избранной покупки не найдена")));
    }

    public Page<UserFavoriteProduct> getFavoriteProducts(Integer userId, int page, int pageSize) {
        return userFavoriteProductRepository.findAllById_UserId(userId, PageRequest.of(page, pageSize));
    }
}
