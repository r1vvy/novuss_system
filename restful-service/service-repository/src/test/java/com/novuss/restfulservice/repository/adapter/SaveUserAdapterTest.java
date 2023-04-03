package com.novuss.restfulservice.repository.adapter;

import com.novuss.restfulservice.repository.converter.MapStructMapper;
import com.novuss.restfulservice.repository.entity.UserEntity;
import com.novuss.restfulservice.repository.repository.jpa.UserJpaRepository;
import com.restfulservice.domain.User;
import com.restfulservice.domain.UserRole;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SaveUserAdapterTest {
    @Mock
    private UserJpaRepository userJpaRepository;

    @Mock
    private MapStructMapper mapper;

    @InjectMocks
    private SaveUserAdapter saveUserAdapter;

    @Test
    public void saveUser() {
        var user = user();
        var userEntity = userEntity();

        when(mapper.userDomainToEntity(user)).thenReturn(userEntity);
        when(userJpaRepository.save(userEntity)).thenReturn(userEntity);
        when(mapper.userEntityToDomain(userEntity)).thenReturn(user);

        User savedUser = saveUserAdapter.saveUser(user);
        assertEquals(user, savedUser);

        verify(mapper).userDomainToEntity(user);
        verify(userJpaRepository).save(userEntity);

        verify(mapper).userEntityToDomain(userEntity);
    }

    private User user() {
        return User.builder()
                .username("username")
                .email("email@email.com")
                .password("password")
                .role(UserRole.USER)
                .build();
    }

    private UserEntity userEntity() {
        return UserEntity.builder()
                .id(UUID.randomUUID())
                .username("username")
                .email("email@email.com")
                .password("password")
                .role(UserRole.USER)
                .build();
    }

}
