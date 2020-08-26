package com.epam.ms.handler.user;

import com.epam.ms.handler.BaseEventHandler;
import com.epam.ms.repository.domain.Event;
import com.epam.ms.util.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.epam.ms.handler.HandlerNameHolder.USER_UNBLOCKED_HANDLER;

@Slf4j
@Component(USER_UNBLOCKED_HANDLER)
public class UserUnblockedHandler extends BaseEventHandler {
    private static final String SUBJECT = "User unblocked";
    private static final String MESSAGE = "The user %s %s has been unblocked";

    @Override
    public void handle(Event event) {
        User unBlockedUser = mediator.getUser(event.getParameters().get("id").toString());
        notifyUser(unBlockedUser);
    }

    private void notifyUser(User user) {
        log.info("Send email to {}", user.getEmail());
        emailSender.send(user.getEmail(), SUBJECT, String.format(MESSAGE, user.getFirstName(), user.getLastName()));
    }
}
