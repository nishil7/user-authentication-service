package com.chegg.userauth.model.response;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
@Getter
public class SignInResponse extends ApiResponse {

    private String token;

    private Integer role;

    @Override
    public void setStatus(@NonNull ApiStatus status) {
        this.status = status;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void role(Integer role) { this.role = role; }

}
