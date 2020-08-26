package com.epam.ms.repository.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class Event implements Serializable {
    private String group;
    private String event;
    private Map<String, Object> parameters;
}
