package com.chegg.userauth.model.response;

import lombok.Getter;

@Getter
public abstract class ApiResponse {

    ApiStatus status;

    String message;

    public abstract void setStatus(ApiStatus status);

    public abstract void setMessage(String message);

}
