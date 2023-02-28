package itmo.blps.mommy.service;

import itmo.blps.mommy.dto.ProductDTO;
import itmo.blps.mommy.entity.Product;
import itmo.blps.mommy.exception.EntityNotFoundException;
import itmo.blps.mommy.mapper.ProductMapper;
import itmo.blps.mommy.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    public Product findProduct(Integer productId) {
        return productRepository
                .findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Unknown product: " + productId));
    }

    public ProductDTO getProduct(Integer productId) {
        return productMapper.toDto(findProduct(productId));
    }

    public List<ProductDTO> suggestProducts(String name, int page, int perPage) {
        List<Product> products = productRepository.findProducts(name.strip(), perPage, (page - 1) * perPage);
        return products
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = productRepository.save(productMapper.fromDto(productDTO));
        return productMapper.toDto(product);
    }

    public ProductDTO updateProduct(ProductDTO productDTO) {
        if (productDTO.getId() == null) {
            throw new RuntimeException("Product id should not be null");
        }
        Product product = findProduct(productDTO.getId());
        product.setName(productDTO.getName());
        product.setWeight(productDTO.getWeight());
        product.setConsumerInfo(productDTO.getConsumerInfo());
        return productMapper.toDto(productRepository.save(product));
    }
}
