package com.example.order.service;

import com.example.order.dto.OrderDto;
import com.example.order.dto.ProductDto;
import com.example.order.dto.UserDto;
import com.example.order.exception.OrderNotFoundException;
import com.example.order.feign.ProductServiceProxy;
import com.example.order.feign.UserServiceProxy;
import com.example.order.model.Order;
import com.example.order.model.OrderProduct;
import com.example.order.repository.OrderRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

//    @Autowired
//    private UserServiceProxy userServiceProxy;

    @Autowired
    private ProductServiceProxy productServiceProxy;

    public OrderDto getByOrderNumber(String orderNumber) {
        final Order order = orderRepository.getDetailsByOrderNumber(orderNumber)
                .orElseThrow(() -> new OrderNotFoundException("Order number: " + orderNumber + " not found"));
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderNumber(order.getOrderNumber());
        orderDto.setDescription(order.getDescription());
        orderDto.setStatus(order.getStatus());
//        UserDto userDto = userServiceProxy.getById(order.getUserId());
//        orderDto.setUsername(userDto.getUsername());
        final Set<OrderProduct> orderProducts = order.getOrderProducts();
        if (!CollectionUtils.isEmpty(orderProducts)) {
            String productIds = orderProducts.stream()
                    .map(op -> op.getId().toString()).collect(Collectors.joining(","));
            List<ProductDto> productDtos = productServiceProxy.getByIds(getAuthorizationToken(), productIds);
            orderDto.getProducts().addAll(productDtos);
        }
        return orderDto;
    }

//    @HystrixCommand(fallbackMethod = "productFallback")
//    private ProductDto getProductById(Long productId) {
//        return productServiceProxy.getById(getAuthorizationToken(), productId);
//    }
//
//    private ProductDto productFallback(Long productId) {
//        // log error or write event to kafka
//        return new ProductDto();
//    }

    private String getAuthorizationToken() {
        String token = "Bearer ";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            OAuth2AuthenticationDetails oauthDetails = (OAuth2AuthenticationDetails) authentication.getDetails();
            token += oauthDetails.getTokenValue();
        }
        return token;
    }
}
