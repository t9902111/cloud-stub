package com.example.product.rest;

import com.example.product.model.Product;
import com.example.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @GetMapping("/ids/{productIds}")
    public List<Product> getByIds(@PathVariable String productIds) {
        List<Long> ids = Arrays.asList(productIds.split(",")).stream()
                .map(Long::parseLong).collect(Collectors.toList());
        return productService.getByIds(ids);
    }

    @PostMapping
    public Product create(@RequestBody Product p) {
        return productService.save(p);
    }
}
