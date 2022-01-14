package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.dto.UserDto;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserMapper userMapper;
    private final UserService userService;

    @GetMapping("/getAll")
    public List<UserDto> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return users.stream()
                .map(user -> userMapper.mapToUserDto(user))
                .collect(Collectors.toList());
    }

    @GetMapping("/get/{id}")
    public UserDto getUser(@PathVariable("id") final Long id) {
        User user = userService.getUser(id).orElse(null);
        return userMapper.mapToUserDto(user);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody final UserDto userDto){
        User createdUser = userMapper.mapToUser(userDto);
        userService.addNewUser(createdUser);
    }

    @PutMapping("/block/{id}")
    public UserDto blockUser(@PathVariable("id") final Long id){
        User user = userService.getUser(id).orElse(null);
        if(user != null){
            user.setBlocked(true);
        }
        return userMapper.mapToUserDto(user);
    }

    @PutMapping("/updateRandomKey/{id}")
    public UserDto updateUserRandomKey(@PathVariable("id") final Long id){
        Random random = new Random();
        User user = userService.getUser(id).orElse(null);
        if (user != null){
            user.setRandomKey(Integer.toString(random.nextInt(99999999)));
        }
        return userMapper.mapToUserDto(user);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto updateUser(@RequestBody final UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        User toSave = userService.addNewUser(user);
        return userMapper.mapToUserDto(toSave);
    }

    @DeleteMapping("/delete/{id}")
    public void removeUser(@PathVariable("id") final Long id){
        userService.removeUser(id);
    }
}