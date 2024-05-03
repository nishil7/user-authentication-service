package com.chegg.userauth.service;

import com.chegg.userauth.model.request.SignUpRequest;
import com.chegg.userauth.model.response.SignUpResponse;

public interface SignUpService {
    SignUpResponse signUp(SignUpRequest userLogin);

}
