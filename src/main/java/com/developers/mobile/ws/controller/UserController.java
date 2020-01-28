package com.developers.mobile.ws.controller;

import com.developers.mobile.ws.request.model.UserDetailsRequestModel;
import com.developers.mobile.ws.response.model.UserRest;
import com.developers.mobile.ws.service.UserService;
import com.developers.mobile.ws.shared.dto.UserDto;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users") // :8080/users...
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public String getUser(){

        return "getUser() called";
    }

    @PostMapping
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails){
        UserRest userResponse = new UserRest();
        UserDto userDto = new UserDto();

        BeanUtils.copyProperties(userDetails, userDto);

        UserDto createdUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createdUser, userResponse);

        return userResponse;
    }

    @PutMapping
    public String updateUser(){
        return "updateUser() called";
    }

    @DeleteMapping
    public String deleteUser() {
        return "deleteUser() called";
    }

}

