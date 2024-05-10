package com.hendisantika.springbootrestclient.controller;

import com.hendisantika.springbootrestclient.model.User2;
import com.hendisantika.springbootrestclient.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<User2>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User2> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(entity -> ResponseEntity.ok(entity))
                .orElse(ResponseEntity.notFound().build());
    }
}
