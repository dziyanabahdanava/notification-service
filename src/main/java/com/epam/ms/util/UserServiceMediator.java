package com.epam.ms.util;

import com.epam.ms.util.dto.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

/**
 * The class is used to send request to the user-service
 */
@Component
public class UserServiceMediator {
    private static final String HOST_URL = "http://localhost:%s";
    private static final String GET_USER_URL = "/users/%s";
    private static final String GET_USERS_URL = "/users";
    private static final Integer USER_SERVICE_PORT = 8080;

    private WebClient client;

    public UserServiceMediator() {
        client = WebClient.builder()
                .baseUrl(String.format(HOST_URL, USER_SERVICE_PORT))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public User getUser(String id) {
        WebClient.RequestBodySpec request = client
                .method(HttpMethod.GET)
                .uri(String.format(GET_USER_URL, id));
        return request
                .retrieve()
                .bodyToMono(User.class)
                .block();
    }

    public List findSubscribedUsers() {
        WebClient client = WebClient.builder()
                .baseUrl(String.format(HOST_URL, USER_SERVICE_PORT))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        WebClient.RequestBodySpec request = client
                .method(HttpMethod.GET)
                .uri(GET_USERS_URL);
        return request
                .retrieve()
                .bodyToMono(List.class)
                .block();
    }
}
