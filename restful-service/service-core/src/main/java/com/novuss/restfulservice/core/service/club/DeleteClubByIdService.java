package com.novuss.restfulservice.core.service.club;

import com.novuss.restfulservice.core.port.in.club.DeleteClubByIdUseCase;
import com.novuss.restfulservice.core.port.out.club.DeleteClubByIdPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteClubByIdService implements DeleteClubByIdUseCase {
    private final DeleteClubByIdPort deleteClubByIdPort;
    @Override
    public void deleteById(String id) {
        deleteClubByIdPort.deleteById(id);
    }
}
