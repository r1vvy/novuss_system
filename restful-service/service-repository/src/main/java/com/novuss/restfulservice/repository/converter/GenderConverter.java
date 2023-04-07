package com.novuss.restfulservice.repository.converter;

import com.novuss.restfulservice.domain.Gender;
import jakarta.persistence.AttributeConverter;
import org.springframework.stereotype.Component;

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
