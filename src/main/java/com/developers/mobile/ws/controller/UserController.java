package com.developers.mobile.ws.controller;

import com.developers.mobile.ws.request.model.UserDetailsRequestModel;
import com.developers.mobile.ws.response.model.UserRest;
import com.developers.mobile.ws.service.UserService;
import com.developers.mobile.ws.shared.dto.UserDto;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(path = "/{id}",
                produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public UserRest getUser(@PathVariable String id){
        try {
            UserRest user = new UserRest();
            UserDto userDto = userService.getUserByUserId(id);
            BeanUtils.copyProperties(userDto , user);
            return user;
        }
        catch (Exception e){
            throw e;
        }
    }

    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
                 produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
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

