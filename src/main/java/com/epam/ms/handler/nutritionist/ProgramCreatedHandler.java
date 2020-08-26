package com.epam.ms.handler.nutritionist;

import com.epam.ms.handler.BaseEventHandler;
import com.epam.ms.repository.domain.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.epam.ms.handler.HandlerNameHolder.NUTRITION_PROGRAM_CREATED_HANDLER;

@Slf4j
@Component(NUTRITION_PROGRAM_CREATED_HANDLER)
public class ProgramCreatedHandler extends BaseEventHandler {
    private static final String SUBJECT = "A new nutrition program is available";
    private static final String MESSAGE = "A new nutrition program (%s calories) is available";

    @Override
    public void handle(Event event) {
        List<Map> subscribedUsers = mediator.findSubscribedUsers();
        notifyUsers(subscribedUsers, Integer.parseInt(event.getParameters().get("calories").toString()));
    }

    private void notifyUsers(List<Map> subscribedUsers, int calories) {
        List<String> emails = subscribedUsers.stream().map(map -> map.get("email").toString()).collect(Collectors.toList());
        log.info("Notify users about new nutrition program creation" + String.join(",", emails));
        emails.forEach(email -> emailSender.send(email,SUBJECT, String.format(MESSAGE, calories)));
    }
}
