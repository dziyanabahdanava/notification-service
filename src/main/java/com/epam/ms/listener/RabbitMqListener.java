package com.epam.ms.listener;

import com.epam.ms.handler.EventHandler;
import com.epam.ms.repository.domain.Event;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Map;


@EnableRabbit
@Component
@Slf4j
public class RabbitMqListener {
    private static final String USER_EVENTS_QUEUE = "userEventsQueue";
    private static final String NUTRITION_EVENTS_QUEUE = "nutritionEventsQueue";

    @Autowired
    private Map<String, EventHandler> handlerMap;

    @Autowired
    private ObjectMapper jacksonMapper;

    @RabbitListener(queues = {USER_EVENTS_QUEUE, NUTRITION_EVENTS_QUEUE})
    public void processUserEventsQueue(String json) {
        Event event = deserialize(json, Event.class);
        log.info("Processing event: {}", event.getEvent());
        log.info("Handler: {}", handlerMap.get(event.getEvent()));
        handlerMap.get(event.getEvent()).handle(event);
    }

    /**
     * The method to deserialize json value to target class
     * @param json json representation for the object
     * @param targetClass target class for deserialization
     * @return
     */
    public Event deserialize(String json, Class<Event> targetClass) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        try {
            return jacksonMapper.readValue(json, targetClass);
        } catch (IOException e) {
            log.error("Read object " + targetClass + " failed", e);
        }
        return null;
    }
}
