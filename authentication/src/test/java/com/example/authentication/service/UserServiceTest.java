package com.example.authentication.service;

import com.example.authentication.dto.UserDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testListAllActive() {
        List<UserDto> allActive = userService.listAllActive();
        Assert.assertTrue(allActive.size() > 0);
    }

    @Test
    public void testUpdateUser() {
        UserDto userDto = new UserDto();
        userDto.setUsername("peter");
        userDto.setFirstName("Peter");
        userDto.setSurname("C");
        userService.updateUser(userDto);
    }
}
