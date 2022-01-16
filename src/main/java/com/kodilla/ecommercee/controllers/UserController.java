package com.kodilla.ecommercee.controllers;

import com.kodilla.ecommercee.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Random;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody final UserDto userDto){
        return new ResponseEntity(userDto, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> blockUser(@PathVariable Long userId, @RequestBody final UserDto userDto){
        userDto.setBlocked(true);
        return new ResponseEntity(userDto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UserDto> generateUserRandomKey(@RequestBody final UserDto userDto){
            Random random = new Random();
            userDto.setRandomKey(Integer.toString(random.nextInt(99999999)));
        return new ResponseEntity(userDto, HttpStatus.OK);
    }
}