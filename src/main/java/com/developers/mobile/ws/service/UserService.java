package com.developers.mobile.ws.service;

import com.developers.mobile.ws.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto getUser(String email);
    UserDto createUser(UserDto user);

}
