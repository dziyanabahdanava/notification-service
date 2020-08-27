package com.epam.ms.handler;

import com.epam.ms.repository.domain.Event;

public interface EventHandler {
    public abstract void handle(Event event);
}
