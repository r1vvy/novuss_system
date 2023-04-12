package com.novuss.restfulservice.out.config;

import com.novuss.restfulservice.out.util.AuthorizationHeaderInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class RestConfig {
    private final AuthorizationHeaderInterceptor authorizationHeaderInterceptor;
    @Bean
    public RestTemplate restTemplate() {
        var restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(authorizationHeaderInterceptor);

        return restTemplate;
    }
}
