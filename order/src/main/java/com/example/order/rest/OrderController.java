package com.example.order.rest;

import com.example.order.dto.OrderDto;
import com.example.order.model.Order;
import com.example.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PreAuthorize("hasAuthority('PRODUCT_READ')")
    @GetMapping("/{orderNumber}")
    public OrderDto getById(@PathVariable String orderNumber) {
        return orderService.getByOrderNumber(orderNumber);
    }
}
