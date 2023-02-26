package itmo.blps.mommy.mapper;

import itmo.blps.mommy.dto.PurchaseRequestDTO;
import itmo.blps.mommy.dto.PurchaseResponseDTO;
import itmo.blps.mommy.entity.Product;
import itmo.blps.mommy.entity.Purchase;
import itmo.blps.mommy.entity.PurchaseStatus;
import itmo.blps.mommy.exception.EntityFoundException;
import itmo.blps.mommy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PurchaseMapper {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductMapper productMapper;

    public Purchase fromDto(PurchaseRequestDTO purchaseRequestDTO) {
        Product product = productService.getProduct(purchaseRequestDTO.getProductId());
        if (product == null) {
            throw new EntityFoundException("Unknown product: " + purchaseRequestDTO.getProductId());
        }
        return new Purchase(
                purchaseRequestDTO.getMinCount(),
                0,
                product,
                PurchaseStatus.CREATED
        );
    }

    public PurchaseResponseDTO toDto(Purchase purchase) {
        return new PurchaseResponseDTO(
                purchase.getId(),
                purchase.getMinCount(),
                purchase.getCurCount(),
                purchase.getPurchaseStatus(),
                productMapper.toDto(purchase.getProduct())
        );
    }

}
