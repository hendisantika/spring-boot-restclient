package com.hendisantika.springbootrestclient.config;

import org.apache.hc.client5.http.impl.async.CloseableHttpAsyncClient;
import org.apache.hc.client5.http.impl.async.HttpAsyncClientBuilder;
import org.apache.hc.client5.http.impl.async.HttpAsyncClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.HttpComponentsClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-restclient
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/5/23
 * Time: 07:41
 * To change this template use File | Settings | File Templates.
 */
@Configuration
public class WebConfig {

    @Bean
    public WebClient webClient() {
        HttpAsyncClientBuilder clientBuilder = HttpAsyncClients.custom();
        //clientBuilder.setDefaultRequestConfig();
        CloseableHttpAsyncClient client = clientBuilder.build();
        ClientHttpConnector connector = new HttpComponentsClientHttpConnector(client);
        WebClient webClient = WebClient.builder().clientConnector(connector).build();
        return webClient;
    }
}
