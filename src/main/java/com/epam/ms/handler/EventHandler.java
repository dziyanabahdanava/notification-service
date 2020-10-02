package com.epam.ms.handler;

import com.epam.ms.repository.domain.Event;

public interface EventHandler {
    void handle(Event event);
}
