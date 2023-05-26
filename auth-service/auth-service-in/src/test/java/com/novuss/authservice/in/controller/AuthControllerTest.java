package com.novuss.authservice.in.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.novuss.authservice.core.port.in.token.AuthorizeRequestByTokenUseCase;
import com.novuss.authservice.core.port.in.token.ExtendTokenExpiryUseCase;
import com.novuss.authservice.core.port.in.user.AuthenticateUserByUsernameUseCase;
import com.novuss.authservice.domain.UserRole;
import com.novuss.authservice.in.dto.request.AuthenticationRequest;
import com.novuss.authservice.in.dto.request.AuthorizationRequest;
import com.novuss.authservice.in.dto.request.RefreshTokenRequest;
import com.novuss.authservice.in.dto.response.AuthResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private AuthenticateUserByUsernameUseCase authenticateUserByUsernameUseCase;

    @Mock
    private AuthorizeRequestByTokenUseCase authorizeRequestByTokenUseCase;

    @Mock
    private ExtendTokenExpiryUseCase extendTokenExpiryUseCase;

    @InjectMocks
    private AuthController authController;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    @Test
    protected void authorize_ValidTokenAndAuthorities_ReturnsAccepted() throws Exception {
        var authorizationHeader = "Bearer valid-token";
        var request = authorizationRequest();

        Mockito.lenient().when(authorizeRequestByTokenUseCase.authorizeByRequiredAuthorities(Mockito.anyString(), Mockito.anyList()))
                .thenReturn(true);

        mockMvc.perform(post("/api/v1/auth/authorize")
                        .header("Authorization", authorizationHeader)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isAccepted());
    }

    @Test
    protected void refresh_InvalidToken_ReturnsBadRequest() throws Exception {

        mockMvc.perform(post("/api/v1/auth/refresh")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString("")))
                .andExpect(status().isBadRequest());
    }

    private AuthenticationRequest authRequest() {
        return new AuthenticationRequest("testuser", "testpassword");
    }

    private AuthorizationRequest authorizationRequest() {
        return new AuthorizationRequest(List.of(
                UserRole.ADMIN,
                UserRole.USER
        ));
    }
    private AuthResponse authResponse() {
        return new AuthResponse("valid-token");
    }
    private RefreshTokenRequest refreshTokenRequest() {
        return new RefreshTokenRequest("valid-token");
    }

    private String asJsonString(Object object) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}

