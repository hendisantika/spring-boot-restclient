package com.hendisantika.springbootrestclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-restclient
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/5/23
 * Time: 07:52
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@EnableScheduling
@Slf4j
public class HttpClientConfig {

    private static final int CONNECT_TIMEOUT = 30000;
    private static final int REQUEST_TIMEOUT = 30000;
    private static final int MAX_TOTAL_CONNECTIONS = 50;
}
