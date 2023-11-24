package com.hendisantika.springbootrestclient.rest;

import com.hendisantika.springbootrestclient.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-restclient
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/25/23
 * Time: 06:26
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class ExternalServiceCaller {

    private final RestClient restClient;

    public ExternalServiceCaller(RestClient.Builder restBuilder, @Value("${user.service.url}") String baseUrl) {
        this.restClient = restBuilder.baseUrl(baseUrl).build();
    }

    @PostMapping("createUser")
    ResponseEntity<Void> createUser(@RequestBody User user) {
        return restClient.post()
                .uri("/createUser")
                .contentType(MediaType.APPLICATION_JSON)
                .body(user)
                .retrieve()
                .toBodilessEntity();
    }

    @GetMapping("/findById/{id}")
    User getUser(@PathVariable String id) {
        return restClient.get()
                .uri("/findById/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(User.class);
    }

    @GetMapping("/entityFindById/{id}")
    ResponseEntity<User> entityFindById(@PathVariable String id) {
        ResponseEntity<User> user = restClient.get()
                .uri("/findById/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(User.class);

        log.info("Status Code " + user.getStatusCode());
        return user;
    }

    @GetMapping("/user-not-found/{id}")
    User userNotFound(@PathVariable String id) {
        return restClient.get()
                .uri("/findById/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, ((request, response) -> {
                    throw new CustomException();
                }))
                .body(User.class);
    }

}
