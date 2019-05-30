package com.example.order.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderDto {
    private String orderNumber;
    private String username;
    private String description;
    private String status;
    private List<ProductDto> products = new ArrayList<>();
}
