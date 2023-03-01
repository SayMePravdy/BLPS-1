package itmo.blps.mommy.controller;

import itmo.blps.mommy.dto.UserFavoriteDto;
import itmo.blps.mommy.entity.UserFavoriteProduct;
import itmo.blps.mommy.service.FavoriteProductService;
import itmo.blps.mommy.validator.ValidProduct;
import itmo.blps.mommy.validator.ValidUser;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/favorites/{userId}/products")
@AllArgsConstructor
public class FavoriteController {
    private FavoriteProductService favoriteProductService;

    @GetMapping("/{productId}")
    public ResponseEntity<UserFavoriteDto> getFavoriteProduct(@PathVariable(name = "userId") @Valid @ValidUser Integer userId,
                                                              @PathVariable(name = "productId") @Valid @ValidProduct Integer productId) {
        return ResponseEntity.ok(favoriteProductService.getFavoriteProduct(userId, productId));
    }

    @PostMapping("/{productId}")
    public ResponseEntity<UserFavoriteDto> addFavoriteProduct(@PathVariable(name = "userId") @Valid @ValidUser Integer userId,
                                                              @PathVariable(name = "productId") @Valid @ValidProduct Integer productId) {
        return ResponseEntity.ok(favoriteProductService.addFavoriteProduct(userId, productId));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<UserFavoriteDto> deleteFavoriteProduct(@PathVariable(name = "userId") @Valid @ValidUser Integer userId,
                                                                 @PathVariable(name = "productId") @Valid @ValidProduct Integer productId) {
        return ResponseEntity.ok(favoriteProductService.deleteFavoriteProduct(userId, productId));
    }

    @GetMapping
    public ResponseEntity<List<UserFavoriteProduct>> getPagedFavoriteProducts(@PathVariable(name = "userId") @Valid @ValidUser Integer userId,
                                                                              @RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                                                              @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize) {
        return ResponseEntity.ok(favoriteProductService.getFavoriteProducts(userId, page, pageSize).getContent());
    }
}
