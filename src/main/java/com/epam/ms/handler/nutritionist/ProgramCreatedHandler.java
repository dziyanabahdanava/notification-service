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

import static com.epam.ms.handler.HandlerNameHolder.NUTRITION_PROGRAM_CREATED_HANDLER;

@Slf4j
@Component(NUTRITION_PROGRAM_CREATED_HANDLER)
public class ProgramCreatedHandler implements EventHandler {
    private static final String SUBJECT = "A new nutrition program is available";
    private static final String MESSAGE = "A new nutrition program (%s calories) is available";

    @Autowired
    private EmailService emailService;
    @Autowired
    private UserServiceClient client;

    @Override
    public void handle(Event event) {
        List<User> subscribedUsers = client.findSubscribedUsers();
        notifyUsers(subscribedUsers, Integer.parseInt(event.getParameters().get("calories").toString()));
    }

    private void notifyUsers(List<User> subscribedUsers, int calories) {
        List<String> emails = subscribedUsers.stream()
                .map(User::getEmail)
                .collect(Collectors.toList());
        log.info("Notify users about new nutrition program creation" + String.join(",", emails));
        emails.forEach(email -> emailService.send(email,SUBJECT, String.format(MESSAGE, calories)));
    }
}
