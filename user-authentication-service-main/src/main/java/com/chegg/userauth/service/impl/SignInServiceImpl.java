package com.chegg.userauth.service.impl;

import com.chegg.userauth.configuration.SignInConfiguration;
import com.chegg.userauth.model.entity.User;
import com.chegg.userauth.model.exceptions.RunTimeException;
import com.chegg.userauth.model.request.SignInRequest;
import com.chegg.userauth.model.response.ApiStatus;
import com.chegg.userauth.model.response.SignInResponse;
import com.chegg.userauth.repository.UserRepository;
import com.chegg.userauth.service.SignInService;
import com.chegg.userauth.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class SignInServiceImpl implements SignInService {

    private SignInConfiguration signInConfiguration;

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setSignInConfiguration(SignInConfiguration signInConfiguration) {
        this.signInConfiguration = signInConfiguration;
    }

    @Override
    public SignInResponse signIn(SignInRequest signInRequest) {
        // Check if user exists
        List<User> users = userRepository.findByUsername(signInRequest.getUsername());
        SignInResponse signInResponse = new SignInResponse();
        if (users.isEmpty()) {
            log.warn("Invalid user. {}", signInRequest);
            throw new RunTimeException.UserNotFoundException("User not found for email: " + signInRequest.getUsername());
        }

        User user = users.get(0);
        // Check if passwords match

        if (!passwordEncoder.matches(signInRequest.getPassword(), user.getPassword())) {
            log.warn("Invalid password for user: {}", signInRequest.getUsername());
            throw new RunTimeException.IncorrectPasswordException("Incorrect password for user: " + signInRequest.getUsername());
        }

        // Generate JWT Token
        String token = JwtUtil.createJWT( user.getUsername(), signInConfiguration.getSignInTTL());
        signInResponse.setToken(token);
        signInResponse.setStatus(ApiStatus.SUCCESS);
        signInResponse.role(user.getRole());
        log.info("{}", user);
        return signInResponse;
    }
}
