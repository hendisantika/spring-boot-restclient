package com.hendisantika.springbootrestclient;

import com.hendisantika.springbootrestclient.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;
import reactor.netty.http.client.HttpClientConfig;

import java.util.List;

@SpringBootTest
@Import({WebConfiguration.class, HttpClientConfig.class})
@Disabled
class SpringBootRestclientApplicationTests {
    @Autowired
    private RestClient restClient;

    /*@Autowired
  RestTemplate restTemplate;*/

    @Test
    public void contextLoads() {
        Assertions.assertNotNull(restClient);
        //Assertions.assertNotNull(restTemplate);
    }

    @Test
    public void testGetAll() {
        List<Employee> employeeList = restClient.get()
                .uri("/employees")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(List.class);

        Assertions.assertNotNull(employeeList);
        Assertions.assertEquals(4, employeeList.size());
    }
}
