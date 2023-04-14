package com.novuss.authservice.core.service.user;

import com.novuss.authservice.core.port.in.user.DeleteUserByIdUseCase;
import com.novuss.authservice.core.port.out.DeleteUserByIdPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteUserByIdService implements DeleteUserByIdUseCase {
    private final DeleteUserByIdPort deleteUserByIdPort;

    @Override
    public void deleteUserById(String id) {
        deleteUserByIdPort.deleteUserById(id);
    }
}
