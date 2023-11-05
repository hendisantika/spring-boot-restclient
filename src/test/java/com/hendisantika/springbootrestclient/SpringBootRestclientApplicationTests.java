package com.hendisantika.springbootrestclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hendisantika.springbootrestclient.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
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

    @Test
    public void testGetAll_WithResponseEntity() {
        ResponseEntity<List> responseEntity = restClient.get()
                .uri("/employees")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(List.class);

        Assertions.assertNotNull(responseEntity.getBody());
        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCode().value());
        Assertions.assertNotEquals(null, responseEntity.getHeaders());
    }

    @Test
    public void testGetById() {
        Employee employee = restClient.get()
                .uri("/employees/1")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(Employee.class);

        Assertions.assertNotNull(employee);
        Assertions.assertEquals(1, employee.getId());
        Assertions.assertEquals("Naruto", employee.getName());
        Assertions.assertEquals("Active", employee.getStatus());
    }

    @Test
    public void testPostAndDelete() {
        Employee newEmployee = new Employee(5L, "Naruto", "active");

        ResponseEntity<Void> responseEntity = restClient.post()
                .uri("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .body(newEmployee)
                .retrieve()
                .toBodilessEntity();

        Assertions.assertEquals(HttpStatus.CREATED.value(), responseEntity.getStatusCode().value());
        Assertions.assertEquals("http://localhost:3000/employees/5",
                responseEntity.getHeaders().get("Location").get(0));

        responseEntity = restClient.delete()
                .uri("/employees/5")
                .retrieve()
                .toBodilessEntity();

        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCode().value());
    }

    @Test
    public void testPut() {
        Employee employee = restClient.get()
                .uri("/employees/1")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(Employee.class);

        String originalName = employee.getName();

        employee.setName("Updated_Name-" + originalName);

        ResponseEntity<Employee> responseEntity = restClient.put()
                .uri("/employees/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(employee)
                .retrieve()
                .toEntity(Employee.class);

        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCode().value());
        Assertions.assertEquals(employee.getName(), responseEntity.getBody().getName());

        employee.setName(originalName);

        restClient.put()
                .uri("/employees/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(employee)
                .retrieve()
                .toEntity(Employee.class);
    }

    @Test
    public void testException() {
        HttpClientErrorException thrown = Assertions.assertThrows(HttpClientErrorException.class,
                () -> {
                    Employee employee = restClient.get()
                            .uri("/employees/5")
                            .accept(MediaType.APPLICATION_JSON)
                            .retrieve()
                            .body(Employee.class);
                });

        Assertions.assertEquals(404, thrown.getStatusCode().value());
    }

    @Test
    public void testExchangeMethod() {
        List<Employee> list = restClient.get()
                .uri("/employees")
                .accept(MediaType.APPLICATION_JSON)
                .exchange((request, response) -> {
                    List apiResponse = null;
                    if (response.getStatusCode().is4xxClientError()
                            || response.getStatusCode().is5xxServerError()) {
                        Assertions.fail("Error occurred in test execution. Check test data and api url.");
                    } else {
                        ObjectMapper mapper = new ObjectMapper();
                        apiResponse = mapper.readValue(response.getBody(), List.class);
                    }
                    return apiResponse;
                });

        Assertions.assertEquals(4, list.size());
    }

}
}
