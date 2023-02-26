package itmo.blps.mommy.repository;

import itmo.blps.mommy.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {

    @Query(value = "select * from purchases pur " +
            "join products pro on pur.product_id = pro.id " +
            "where pro.name like concat('%', :name, '%') " +
            "limit :limit offset :offset",
            nativeQuery = true)
    List<Purchase> findPurchases(
            @Param("name") String name,
            @Param("limit") Integer limit,
            @Param("offset") Integer offset
    );

}
