package com.hendisantika.springbootrestclient.controller;

import com.hendisantika.springbootrestclient.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-restclient
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 5/10/24
 * Time: 18:13
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class User2Controller {

    // Declare the service as final to ensure its immutability
    private final UserService userService;
}
