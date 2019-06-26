package com.example.authentication.rest;

import com.example.authentication.dto.UserDto;
import com.example.authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

//    @GetMapping
//    public Principal user(Principal user) {
//        return user;
//    }

    @GetMapping()
    public List<UserDto> listAllActive() {
        return userService.listAllActive();
    }

    @GetMapping("/{username}")
    public UserDto getUserByUsername(@PathVariable @Size(min = 2) String username) {
        return userService.findByUsername(username);
    }

    @PatchMapping
    public UserDto updateUser(@Valid @RequestBody UserDto userDto) {
        return userService.updateUser(userDto);
    }
}
