package com.novuss.authservice.repository.converter;

import com.novuss.authservice.domain.User;
import com.novuss.authservice.repository.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapStructMapper {
    User userEntityToDomain(UserEntity userEntity);
    UserEntity userDomainToEntity(User user);
}
