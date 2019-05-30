package com.example.order.dto;

import com.example.order.model.OrderProduct;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String pid;
    private String productName;
    private String description;
    private String imageUrl;
    private BigDecimal price;
    private Integer qty;
}
