package com.novuss.restfulservice.repository.adapter;

import com.novuss.restfulservice.core.port.out.SaveUserPort;
import com.novuss.restfulservice.repository.converter.MapStructMapper;
import com.novuss.restfulservice.repository.repository.jpa.UserJpaRepository;
import com.restfulservice.domain.User;
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
