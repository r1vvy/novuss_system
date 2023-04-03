package com.novuss.authservice.repository.adapter;

import com.novuss.authservice.core.port.out.SaveUserPort;
import com.novuss.authservice.domain.User;
import com.novuss.authservice.repository.converter.MapStructMapper;
import com.novuss.authservice.repository.repository.UserJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SaveUserAdapter implements SaveUserPort {
    private final UserJpaRepository userJpaRepository;
    private final MapStructMapper mapper;

    @Override
    public User saveUser(User user) {
        var userEntity = mapper.userDomainToEntity(user);
        userJpaRepository.save(userEntity);

        return mapper.userEntityToDomain(userEntity);
    }
}
