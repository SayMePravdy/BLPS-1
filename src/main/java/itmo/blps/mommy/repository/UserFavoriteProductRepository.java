package itmo.blps.mommy.repository;

import itmo.blps.mommy.entity.UserFavoriteProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserFavoriteProductRepository extends JpaRepository<UserFavoriteProduct, Integer> {
    Optional<UserFavoriteProduct> findById_UserIdAndId_ProductId(Integer userId, Integer productId);

    @Transactional
    Optional<UserFavoriteProduct> deleteById_UserIdAndId_ProductId(Integer userId, Integer productId);

    Page<UserFavoriteProduct> findAllById_UserId(Integer userId, Pageable pageable);
}
