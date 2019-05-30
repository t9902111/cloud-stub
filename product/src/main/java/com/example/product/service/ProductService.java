package com.example.product.service;

import com.example.product.exception.ProductNotFoundException;
import com.example.product.model.Product;
import com.example.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id: " + id + " not found."));
    }

    public List<Product> getByIds(List<Long> ids) {
        return productRepository.getByIds(ids);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Product save(Product p) {
        return productRepository.save(p);
    }
}
