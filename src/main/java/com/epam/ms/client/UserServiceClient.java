package com.epam.ms.client;

import com.epam.ms.client.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

/**
 * The class is used to send request to the user-service
 */
@Component
public class UserServiceClient {
    @Autowired
    private WebClient client;

    public User getUser(String id) {
        WebClient.RequestBodySpec request = client
                .method(HttpMethod.GET)
                .uri(String.format("/users/%s", id));
        return request
                .retrieve()
                .bodyToMono(User.class)
                .block();
    }

    public List<User> findSubscribedUsers() {
        return client
                .method(HttpMethod.GET)
                .uri("/users")
                .retrieve()
                .bodyToFlux(User.class) // Flux<User>
                .collectList() // Mono<List<User>>
                .block();
    }
}
