package itmo.blps.mommy.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "purchases")
@Data
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "min_count")
    private Integer minCount;

    @Column(name = "cur_count")
    private Integer curCount;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private PurchaseStatus purchaseStatus;

    public Purchase(Integer minCount, Integer curCount, Product product, PurchaseStatus purchaseStatus) {
        this.minCount = minCount;
        this.curCount = curCount;
        this.product = product;
        this.purchaseStatus = purchaseStatus;
    }

    public Purchase() {
    }
}
