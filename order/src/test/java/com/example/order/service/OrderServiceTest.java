package com.example.order.service;

import com.example.order.dto.OrderDto;
import com.example.order.model.Order;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;

    @Test
    public void testGetById() {
        OrderDto dto = orderService.getByOrderNumber("ORDER00001");
        Assert.assertEquals(3, dto.getProducts().size());
    }
}
