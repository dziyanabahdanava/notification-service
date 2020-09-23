package com.epam.ms.handler.nutritionist;

import com.epam.ms.handler.EventHandler;
import com.epam.ms.client.model.User;
import com.epam.ms.repository.domain.Event;
import com.epam.ms.service.EmailService;
import com.epam.ms.client.UserServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.epam.ms.handler.HandlerNameHolder.NUTRITION_PROGRAM_UPDATED_HANDLER;

@Slf4j
@Component(NUTRITION_PROGRAM_UPDATED_HANDLER)
public class ProgramUpdatedHandler implements EventHandler {
    private static final String SUBJECT = "We finalized the menu";
    private static final String MESSAGE = "The nutrition program %s is now available in an improved version";

    @Autowired
    protected EmailService emailService;
    @Autowired
    protected UserServiceClient client;

    @Override
    public void handle(Event event) {
        List<User> subscribedUsers = client.findSubscribedUsers();
        notifyUsers(subscribedUsers, event.getParameters().get("id").toString());
    }

    private void notifyUsers(List<User> subscribedUsers, String id) {
        List<String> emails = subscribedUsers.stream()
                .map(User::getEmail).collect(Collectors.toList());
        emails.forEach(email -> emailService.send(email,SUBJECT, String.format(MESSAGE, id)));
    }
}
