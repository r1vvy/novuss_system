package com.novuss.authservice.in.dto.request;

public record UpdateUserInRequest(
    String username,
    String email,
    String password
) {
}
