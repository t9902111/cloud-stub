package com.example.authentication.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OAuthUserDetailServiceTest {

    @Autowired
    private UserDetailsService userDetailsService;

    @Test
    public void testFindByUsername() {
        UserDetails peter = userDetailsService.loadUserByUsername("peter");
        peter.getAuthorities().stream().forEach(System.out::println);
        Assert.assertEquals("peter", peter.getUsername());
    }
}
