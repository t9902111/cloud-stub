package com.example.order.feign;

import com.example.order.dto.ProductDto;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductServiceFallback implements ProductServiceProxy {
    @Override
    public ProductDto getById(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        return new ProductDto();
    }

    @Override
    public List<ProductDto> getByIds(@RequestHeader("Authorization")String token, @PathVariable String ids) {
        return new ArrayList<>();
    }
}
