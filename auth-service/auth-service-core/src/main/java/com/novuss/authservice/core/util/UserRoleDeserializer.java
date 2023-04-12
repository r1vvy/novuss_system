package com.novuss.authservice.core.util;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.novuss.authservice.domain.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserRoleDeserializer extends StdDeserializer<List<UserRole>> {
    public UserRoleDeserializer() {
        this(null);
    }

    public UserRoleDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public List<UserRole> deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        List<UserRole> roles = new ArrayList<>();
        if (node.isArray()) {
            for (JsonNode roleNode : node) {
                String roleStr = roleNode.asText();
                UserRole role = UserRole.valueOf(roleStr);
                roles.add(role);
            }
        } else {
            String roleStr = node.asText();
            UserRole role = UserRole.valueOf(roleStr);
            roles.add(role);
        }
        return roles;
    }
}
