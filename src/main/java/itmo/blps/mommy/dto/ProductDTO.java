package itmo.blps.mommy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDTO {

    private Integer id;

    private String name;

    private Float weight;

    private String consumerInfo;

}
