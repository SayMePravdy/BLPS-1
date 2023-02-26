package itmo.blps.mommy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PurchaseRequestDTO {

    private Integer minCount;

    private Integer productId;
}
