package com.hendisantika.springbootrestclient;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-restclient
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/5/23
 * Time: 07:50
 * To change this template use File | Settings | File Templates.
 */
@TestConfiguration
class WebConfiguration {

    @Value("${REMOTE_BASE_URI:http://localhost:3000}")
    String baseURI;
    @Autowired
    private CloseableHttpClient httpClient;
}
