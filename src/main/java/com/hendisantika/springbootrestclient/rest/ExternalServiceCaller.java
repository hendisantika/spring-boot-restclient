package com.hendisantika.springbootrestclient.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
