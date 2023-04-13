package com.novuss.authservice.security.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

@Configuration
public class SecretKeyUtils {
    private static final String ALGORITHM = "HS256";
    private static final int KEY_SIZE = 256;

    @Bean
    public SecretKeySpec secretKeySpec() {
        var secureRandom = new SecureRandom();
        var key = new byte[KEY_SIZE / 8];
        secureRandom.nextBytes(key);
        return new SecretKeySpec(key, ALGORITHM);
    }
}
