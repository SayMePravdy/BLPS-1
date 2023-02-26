package itmo.blps.mommy.repository;

import itmo.blps.mommy.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
