package com.portfolio.my_porfolio_backend.dto;

import com.portfolio.my_porfolio_backend.model.User;

/**
 * UserMapper
 */
public class UserMapper {

    public static UserDto toDto(User user) {
        if (user == null) {
            return null;
        }
        UserDto userDto = new UserDto();

        userDto.setUsername(user.getUsername());
        return userDto;
    }

    public static User toEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        User user = new User();

        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());

        return user;
    }
}
