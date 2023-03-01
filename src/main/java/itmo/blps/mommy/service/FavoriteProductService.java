package itmo.blps.mommy.service;

import itmo.blps.mommy.dto.UserFavoriteDto;
import itmo.blps.mommy.entity.User;
import itmo.blps.mommy.entity.UserFavoriteProduct;
import itmo.blps.mommy.exception.EntityNotFoundException;
import itmo.blps.mommy.mapper.UserFavoriteMapper;
import itmo.blps.mommy.repository.UserFavoriteProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
public class FavoriteProductService {
    private UserFavoriteProductRepository userFavoriteProductRepository;
    private UserFavoriteMapper userFavoriteMapper;
    private UserService userService;

    public UserFavoriteDto addFavoriteProduct(Integer productId) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(userName);
        UserFavoriteProduct userFavoriteProduct = new UserFavoriteProduct(user.getId(), productId);
        userFavoriteProduct.setDateCreated(Instant.now());
        return userFavoriteMapper.toDto(userFavoriteProductRepository.save(userFavoriteProduct));
    }

    public UserFavoriteDto getFavoriteProduct(Integer productId) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(userName);
        return userFavoriteMapper.toDto(userFavoriteProductRepository.findById_UserIdAndId_ProductId(user.getId(), productId)
                .orElseThrow(() -> new EntityNotFoundException("Сущность избранной покупки не найдена")));
    }

    public UserFavoriteDto deleteFavoriteProduct(Integer productId) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(userName);
        return userFavoriteMapper.toDto(userFavoriteProductRepository.deleteById_UserIdAndId_ProductId(user.getId(), productId)
                .orElseThrow(() -> new EntityNotFoundException("Сущность избранной покупки не найдена")));
    }

    public Page<UserFavoriteProduct> getFavoriteProducts(int page, int pageSize) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(userName);
        return userFavoriteProductRepository.findAllById_UserId(user.getId(), PageRequest.of(page, pageSize));
    }
}
