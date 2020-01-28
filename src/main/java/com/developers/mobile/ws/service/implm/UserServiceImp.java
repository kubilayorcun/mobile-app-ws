package com.developers.mobile.ws.service.implm;

import com.developers.mobile.ws.UserRepository;
import com.developers.mobile.ws.io.entity.UserEntity;
import com.developers.mobile.ws.service.UserService;
import com.developers.mobile.ws.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto user){

        if (userRepository.findByEmail(user.getEmail()) != null){
            throw new RuntimeException("Reccord already exist");
        }

        UserEntity userEntity = new UserEntity();

        BeanUtils.copyProperties(user, userEntity);

        userEntity.setEncryptedPassword("test");

        userEntity.setUserId("testUserId");

        UserEntity storedUserDetails = userRepository.save(userEntity);

        UserDto returnUser = new UserDto();

        BeanUtils.copyProperties(storedUserDetails, returnUser);

        return returnUser;
    }
}
