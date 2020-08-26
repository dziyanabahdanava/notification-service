package com.epam.ms.handler;

import com.epam.ms.repository.domain.Event;
import com.epam.ms.util.EmailSender;
import com.epam.ms.util.UserServiceMediator;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseEventHandler {
    @Autowired
    protected EmailSender emailSender;
    @Autowired
    protected UserServiceMediator mediator;


    public abstract void handle(Event event);
}
