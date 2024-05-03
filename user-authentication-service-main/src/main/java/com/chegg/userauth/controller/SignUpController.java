package com.chegg.userauth.controller;
import com.chegg.userauth.model.request.SignUpRequest;
import com.chegg.userauth.model.response.SignUpResponse;
import com.chegg.userauth.repository.UserRepository;
import com.chegg.userauth.service.SignUpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.chegg.userauth.model.entity.User;

import java.util.List;


@RestController
@RequestMapping
@Slf4j
public class SignUpController {

    private final UserRepository userRepository;
    private SignUpService signUpService;

    public SignUpController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setSignUpService(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @PostMapping(value = "/signUp", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody SignUpRequest signUpRequest) {
        List<User> existingUsers = userRepository.findByUsername(signUpRequest.getUsername());
        if (!existingUsers.isEmpty()) {
            // Email already exists, return error response
            SignUpResponse signUpResponse = new SignUpResponse();
            signUpResponse.setMessage("Username already exists");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(signUpResponse);
        }

        log.info("Received SignUp request {}", signUpRequest);
        SignUpResponse signUpResponse = signUpService.signUp(signUpRequest);
        signUpResponse.setMessage(signUpResponse.getMessage());
        signUpResponse.setStatus(signUpResponse.getStatus());
        return ResponseEntity.ok(signUpResponse);
    }
}