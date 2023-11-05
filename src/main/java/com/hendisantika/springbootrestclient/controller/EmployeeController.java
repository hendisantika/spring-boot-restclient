package com.hendisantika.springbootrestclient.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-restclient
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/5/23
 * Time: 07:42
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {

    private final WebClient webClient;

    public void getEmployee() {
    }
}
