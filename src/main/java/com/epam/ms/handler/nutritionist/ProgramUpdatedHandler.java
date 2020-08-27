package com.epam.ms.handler.nutritionist;

import com.epam.ms.handler.EventHandler;
import com.epam.ms.repository.domain.Event;
import com.epam.ms.util.EmailSender;
import com.epam.ms.util.UserServiceMediator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.epam.ms.handler.HandlerNameHolder.NUTRITION_PROGRAM_UPDATED_HANDLER;

@Slf4j
@Component(NUTRITION_PROGRAM_UPDATED_HANDLER)
public class ProgramUpdatedHandler implements EventHandler {
    private static final String SUBJECT = "The program is improved";
    private static final String MESSAGE = "The nutrition program %s is now available in an improved version";

    @Autowired
    protected EmailSender emailSender;
    @Autowired
    protected UserServiceMediator mediator;

    @Override
    public void handle(Event event) {
        List<Map> subscribedUsers = mediator.findSubscribedUsers();
        notifyUsers(subscribedUsers, event.getParameters().get("id").toString());
    }

    private void notifyUsers(List<Map> subscribedUsers, String id) {
        List<String> emails = subscribedUsers.stream().map(map -> map.get("email").toString()).collect(Collectors.toList());
        emails.forEach(email -> emailSender.send(email,SUBJECT, String.format(MESSAGE, id)));
    }
}
