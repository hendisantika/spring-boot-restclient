package com.hendisantika.springbootrestclient.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class EmployeeController {

    @GetMapping("/test")
    public String test() {
        return "Employee Controller is working!";
    }
}
