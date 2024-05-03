package com.chegg.userauth.model.response;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
@Getter
public class SignUpResponse extends ApiResponse {

    private String token;
    public void setStatus(@NonNull ApiStatus status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
