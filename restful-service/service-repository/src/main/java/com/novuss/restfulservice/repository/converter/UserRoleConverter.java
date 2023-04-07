package com.novuss.restfulservice.repository.converter;

import com.novuss.restfulservice.domain.UserRole;
import jakarta.persistence.AttributeConverter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserRoleConverter implements AttributeConverter<Set<UserRole>, String> {

    @Override
    public String convertToDatabaseColumn(Set<UserRole> attribute) {
        return attribute.stream()
                .map(UserRole::toString)
                .collect(Collectors.joining(","));
    }

    @Override
    public Set<UserRole> convertToEntityAttribute(String dbData) {
        String[] roles = dbData.split(",");
        return new HashSet<>(Arrays.asList(roles))
                .stream()
                .map(UserRole::valueOf)
                .collect(Collectors.toSet());
    }


}

