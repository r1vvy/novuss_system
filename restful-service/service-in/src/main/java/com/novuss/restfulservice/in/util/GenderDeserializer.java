package com.novuss.restfulservice.in.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.novuss.restfulservice.domain.Gender;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GenderDeserializer extends JsonDeserializer<Gender> {

    @Override
    public Gender deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getValueAsString();

        return Gender.valueOf(value.toUpperCase());
    }
}
