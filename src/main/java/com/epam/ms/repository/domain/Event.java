package com.epam.ms.repository.domain;

import lombok.Data;

import java.util.Map;

@Data
public class Event {
    private String group;
    private String event;
    private Map<String, Object> parameters;
}
