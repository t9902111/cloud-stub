package com.example.authentication.dto;

import com.example.authentication.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    @NotEmpty(message = "User name can not be empty")
    private String username;
    @NotEmpty(message = "First name can not be empty")
    @Size(message = "First name must be between 2 and 10", min = 2, max = 10)
    private String firstName;
    @NotEmpty(message = "Surname can not be empty")
    @Size(message = "Surname must be between 2 and 10", min = 2, max = 10)
    private String surname;

    public static UserDto of(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getFirstName(), user.getSurname());
    }
}
