package itmo.blps.mommy.service;

import itmo.blps.mommy.entity.Product;
import itmo.blps.mommy.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product getProduct(Integer productId) {
        return productRepository.getById(productId);
    }
}
