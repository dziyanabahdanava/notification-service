package com.epam.ms.handler.user;

import com.epam.ms.handler.EventHandler;
import com.epam.ms.repository.domain.Event;
import com.epam.ms.util.EmailSender;
import com.epam.ms.util.UserServiceMediator;
import com.epam.ms.util.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.epam.ms.handler.HandlerNameHolder.USER_BLOCKED_HANDLER;

@Slf4j
@Component(USER_BLOCKED_HANDLER)
public class UserBlockedHandler implements EventHandler {
    private static final String SUBJECT = "User blocked";
    private static final String MESSAGE = "The user %s %s has been blocked";

    @Autowired
    private EmailSender emailSender;
    @Autowired
    private UserServiceMediator mediator;

    @Override
    public void handle(Event event) {
        User blockedUser = mediator.getUser(event.getParameters().get("id").toString());
        notifyUser(blockedUser);
    }

    private void notifyUser(User user) {
        log.info("Send email to {}", user.getEmail());
        emailSender.send(user.getEmail(), SUBJECT, String.format(MESSAGE, user.getFirstName(), user.getLastName()));
    }
}
