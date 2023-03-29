package itmo.blps.mommy.service;

import itmo.blps.mommy.dto.UserFavoriteDto;
import itmo.blps.mommy.entity.User;
import itmo.blps.mommy.entity.UserFavoriteProduct;
import itmo.blps.mommy.exception.EntityNotFoundException;
import itmo.blps.mommy.mapper.UserFavoriteMapper;
import itmo.blps.mommy.repository.ProductRepository;
import itmo.blps.mommy.service.database.UserFavoriteDbService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
public class FavoriteProductService {
    private UserFavoriteDbService userFavoriteDbService;
    private UserFavoriteMapper userFavoriteMapper;
    private UserService userService;
    private ProductRepository productRepository;

    public UserFavoriteDto addFavoriteProduct(Integer productId) {
        productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Сущность продукта не найдена"));
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(userName);
        UserFavoriteProduct userFavoriteProduct = new UserFavoriteProduct(user.getId(), productId);
        userFavoriteProduct.setDateCreated(Instant.now());
        return userFavoriteMapper.toDto(userFavoriteDbService.createFavoriteProduct(userFavoriteProduct));
    }


    public void deleteFavoriteProduct(Integer productId) {
        productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Сущность продукта не найдена"));
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(userName);
        userFavoriteDbService.findUserIdAndProductId(user.getId(), productId);
        userFavoriteDbService.deleteFavoriteProduct(user.getId(), productId);
    }

    public Page<UserFavoriteProduct> getFavoriteProducts(int page, int pageSize) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(userName);
        return userFavoriteDbService.findAllByUserId(user.getId(), PageRequest.of(page, pageSize));
    }
}
