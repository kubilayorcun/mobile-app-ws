package com.developers.mobile.ws.service.implm;

import com.developers.mobile.ws.UserRepository;
import com.developers.mobile.ws.Utils;
import com.developers.mobile.ws.io.entity.UserEntity;
import com.developers.mobile.ws.service.UserService;
import com.developers.mobile.ws.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils utils;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserDto createUser(UserDto user){

        if (userRepository.findByEmail(user.getEmail()) != null){
            throw new RuntimeException("Reccord already exist");
        }

        UserEntity userEntity = new UserEntity();

        // Map DataTransferObject ot Entity
        BeanUtils.copyProperties(user, userEntity);
        String publicUserId = utils.generateUserId(30);
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userEntity.setUserId(publicUserId);
        UserEntity storedUserDetails = userRepository.save(userEntity);
        UserDto returnUser = new UserDto();
        BeanUtils.copyProperties(storedUserDetails, returnUser);

        return returnUser;
    }
}
