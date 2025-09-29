package com.hendisantika.springbootrestclient.service;

import com.hendisantika.springbootrestclient.model.User2;
import com.hendisantika.springbootrestclient.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-restclient
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 5/10/24
 * Time: 18:05
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User2> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User2> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User2 saveUser(User2 user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
