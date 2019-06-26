package com.example.authentication.service;

import com.example.authentication.dto.UserDto;
import com.example.authentication.model.User;
import com.example.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserDto> listAllActive() {
        return userRepository.findAllActive().stream().map(UserDto::of).collect(Collectors.toList());
    }

    public UserDto findByUsername(String username) {
        return UserDto.of(userRepository.findByUsername(username));
    }

    public UserDto updateUser(UserDto userDto) {
        User user = userRepository.findByUsername(userDto.getUsername());
        user.setFirstName(userDto.getFirstName());
        user.setSurname(userDto.getSurname());
        return UserDto.of(userRepository.save(user));
    }
}
