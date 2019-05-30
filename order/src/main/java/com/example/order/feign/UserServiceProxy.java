package com.example.order.feign;

import com.example.order.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@Component
//@FeignClient(name = "user-service")
public interface UserServiceProxy {
    @GetMapping("/user/{id}")
    public UserDto getById(@PathVariable Long id);
}
