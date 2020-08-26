package com.epam.ms.util;

import com.epam.ms.exception.ServerException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * The class to deserialize objects.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class Deserializer {
    @NonNull
    private ObjectMapper jacksonMapper;

    /**
     * The method to deserialize json value to target class
     * @param json json representation for the object
     * @param targetClass target class for deserialization
     * @param <T> the type to be returned
     * @return
     */
    public <T> T deserialize(String json, Class<T> targetClass) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        try {
            return jacksonMapper.readValue(json, targetClass);
        } catch (IOException e) {
            log.error("Read object " + targetClass + " failed", e);
        }
        return null;
    }
}
