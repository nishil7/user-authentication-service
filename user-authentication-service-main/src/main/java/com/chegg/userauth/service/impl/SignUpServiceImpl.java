package com.chegg.userauth.service.impl;

import com.chegg.userauth.configuration.SignInConfiguration;
import com.chegg.userauth.model.entity.User;
import com.chegg.userauth.model.request.SignUpRequest;
import com.chegg.userauth.model.response.ApiStatus;
import com.chegg.userauth.model.response.SignUpResponse;
import com.chegg.userauth.repository.UserRepository;
import com.chegg.userauth.service.SignUpService;
import com.chegg.userauth.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.SQLOutput;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class SignUpServiceImpl implements SignUpService {

    //private SignUpConfiguration signUpConfiguration;

    private UserRepository userRepository;

    private  SignInConfiguration signInConfiguration;

    private  PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public SignUpResponse signUp(SignUpRequest signUpRequest) {


        SignUpResponse signUpResponse = new SignUpResponse();

        // Check if user already exists
        List<User> existingUsers = userRepository.findByUsername(signUpRequest.getUsername());
        if (!existingUsers.isEmpty()) {
            log.warn("User with email {} already exists", signUpRequest.getUsername());
            signUpResponse.setStatus(ApiStatus.FAIL);
            signUpResponse.setMessage("User already exists");
            return signUpResponse;
        }

        // Create a new user entity
        User newUser = new User();
        newUser.setName(signUpRequest.getName());
        newUser.setUsername(signUpRequest.getUsername());
        String encodedPassword = passwordEncoder.encode(signUpRequest.getPassword());
        newUser.setPassword(encodedPassword);
        newUser.setRole(0);

        log.info("Saving...");
        userRepository.save(newUser);
        log.info("Saved...");

        signUpResponse.setStatus(ApiStatus.SUCCESS);

        log.info("User signed up successfully: {}", newUser);
        return signUpResponse;
    }
}


