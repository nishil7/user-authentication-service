package com.chegg.userauth.configuration;

import lombok.Getter;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class SignInConfiguration {

    private final Long SignInTTL = 86400L; // 1 day

}
