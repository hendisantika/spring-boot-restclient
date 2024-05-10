package com.hendisantika.springbootrestclient.repository;

import com.hendisantika.springbootrestclient.model.User2;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-restclient
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 5/10/24
 * Time: 18:04
 * To change this template use File | Settings | File Templates.
 */
public interface UserRepository extends JpaRepository<User2, Long> {
}
