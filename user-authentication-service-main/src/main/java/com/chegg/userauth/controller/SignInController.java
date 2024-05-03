package com.chegg.userauth.controller;

import com.chegg.userauth.model.User;
import com.chegg.userauth.model.request.SignInRequest;
import com.chegg.userauth.model.response.SignInResponse;
import com.chegg.userauth.service.SignInService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping()
@Slf4j
public class SignInController {

    private SignInService signInService;

    @Autowired
    public void setSignInService(SignInService signInService) {
        this.signInService = signInService;
    }

    @GetMapping(value = "/signIn", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SignInResponse> signIn(@RequestHeader(required = true) String username, @RequestHeader(required = true) String password) {
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setUsername(username);
        signInRequest.setPassword(password);
        log.info("Received SignIn request {}", signInRequest);
        SignInResponse signInResponse = signInService.signIn(signInRequest);
        signInResponse.setMessage(signInResponse.getMessage());
        signInResponse.setStatus(signInResponse.getStatus());
        signInResponse.setToken(signInResponse.getToken());
        signInResponse.role(signInResponse.getRole());
        return ResponseEntity.ok(signInResponse);
    }

}
