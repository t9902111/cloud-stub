package com.example.order.feign;

import com.example.order.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@Component
@FeignClient(name = "product-service", fallback = ProductServiceFallback.class)
public interface ProductServiceProxy {
    @GetMapping("/product/{id}")
    public ProductDto getById(@RequestHeader("Authorization") String token, @PathVariable Long id);

    @GetMapping("/product/ids/{ids}")
    public List<ProductDto> getByIds(@RequestHeader("Authorization") String token, @PathVariable String ids);
}

