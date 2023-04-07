package com.novuss.restfulservice.repository.converter;

import com.restfulservice.domain.Gender;
import com.restfulservice.domain.UserRole;
import jakarta.persistence.AttributeConverter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class GenderConverter implements AttributeConverter<Gender, String> {
    @Override
    public String convertToDatabaseColumn(Gender gender) {
        return gender.name();
    }

    @Override
    public Gender convertToEntityAttribute(String dbData) {
        return Gender.valueOf(dbData);
    }

}
