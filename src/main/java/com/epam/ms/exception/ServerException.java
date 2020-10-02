package com.epam.ms.exception;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.nonNull;


@Data
public class ServerException extends RuntimeException {
    private String errorCode;
    private Map<String, String> userParameters = new HashMap<>();
    private String overrideMessage;
    private String overrideHTTPStatusCode;

    public ServerException() {
    }

    public ServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServerException(String errorCode, Map<String, Object> parameters) {
        this();
        this.errorCode = errorCode;
        this.setParameters(parameters);
    }

    public ServerException(String errorCode, Map<String, Object> parameters, Throwable cause) {
        super(null, cause);
        this.errorCode = errorCode;
        this.setParameters(parameters);
    }

    public void setParameters(Map<String, Object> parameters) {
        if(nonNull(parameters)) {
            parameters.entrySet().stream()
                    .filter(entry -> nonNull(entry.getValue()))
                    .forEach(entry -> this.addUserParameter(entry.getKey(), entry.getValue().toString()));
        }
    }

    public void addUserParameter(String key, String value) {
        this.getUserParameters().put(key, value);
    }
}
