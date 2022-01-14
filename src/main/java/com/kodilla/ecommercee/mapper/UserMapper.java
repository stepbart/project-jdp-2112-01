package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public User mapToUser(final UserDto userDto){

        return new User(userDto.getId(),
                userDto.getUserName(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail(),
                userDto.getPhoneNumber(),
                userDto.getAddress(),
                userDto.isBlocked(),
                userDto.getRandomKey(),
                userDto.getCarts(),
                userDto.getOrders());
    }

    public UserDto mapToUserDto(final User user){
        return new UserDto(user.getId(),
                user.getUserName(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getAddress(),
                user.isBlocked(),
                user.getRandomKey(),
                user.getOrders(),
                user.getCarts());
    }
}
