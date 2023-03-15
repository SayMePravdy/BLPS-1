package itmo.blps.mommy.controller;

import itmo.blps.mommy.dto.UserFavoriteDto;
import itmo.blps.mommy.entity.UserFavoriteProduct;
import itmo.blps.mommy.service.FavoriteProductService;
import itmo.blps.mommy.validator.ValidProduct;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/favorites/products")
@AllArgsConstructor
public class FavoriteController {
    private FavoriteProductService favoriteProductService;

    @PostMapping("/{productId}")
    public ResponseEntity<UserFavoriteDto> addFavoriteProduct(@PathVariable(name = "productId") @Valid @ValidProduct Integer productId) {
        return ResponseEntity.ok(favoriteProductService.addFavoriteProduct(productId));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteFavoriteProduct(@PathVariable(name = "productId") @Valid @ValidProduct Integer productId) {
        favoriteProductService.deleteFavoriteProduct(productId);
        return ResponseEntity.ok().body("Успешно удален товар из избранного!");
    }

    @GetMapping
    public ResponseEntity<List<UserFavoriteProduct>> getPagedFavoriteProducts(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                                                              @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize) {
        return ResponseEntity.ok(favoriteProductService.getFavoriteProducts(page, pageSize).getContent());
    }
}
