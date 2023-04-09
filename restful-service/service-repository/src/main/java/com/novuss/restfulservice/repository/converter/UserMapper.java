package com.novuss.restfulservice.repository.converter;

import com.novuss.restfulservice.domain.User;
import com.novuss.restfulservice.repository.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User userEntityToDomain(UserEntity userEntity);
    UserEntity userDomainToEntity(User user);
}
