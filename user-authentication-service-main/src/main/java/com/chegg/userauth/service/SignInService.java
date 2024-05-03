package com.chegg.userauth.service;

import com.chegg.userauth.model.request.SignInRequest;
import com.chegg.userauth.model.response.SignInResponse;

public interface SignInService {

    SignInResponse signIn(SignInRequest userLogin);
}
