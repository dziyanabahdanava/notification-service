package com.epam.ms.listener;

import com.epam.ms.handler.BaseEventHandler;
import com.epam.ms.repository.domain.Event;
import com.epam.ms.util.Deserializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;


@EnableRabbit
@Component
@Slf4j
public class RabbitMqListener {
    private static final String USER_EVENTS_QUEUE = "userEventsQueue";
    private static final String NUTRITION_EVENTS_QUEUE = "nutritionEventsQueue";

    @Autowired
    private Deserializer deserializer;
    @Autowired
    private Map<String, BaseEventHandler> handlerMap;

    @RabbitListener(queues = USER_EVENTS_QUEUE)
    public void processUserEventsQueue(String json) {
        Event event = deserializer.deserialize(json, Event.class);
        log.info("Processing event: {}", event.getEvent());
        log.info("Handler: {}", handlerMap.get(event.getEvent()));
        handlerMap.get(event.getEvent()).handle(event);
    }

    @RabbitListener(queues = NUTRITION_EVENTS_QUEUE)
    public void processNutritionEventsQueue(String json) {
        Event event = deserializer.deserialize(json, Event.class);
        log.info("Processing event: {}", event.getEvent());
        log.info("Handler: {}", handlerMap.get(event.getEvent()));
        handlerMap.get(event.getEvent()).handle(event);
    }
}
