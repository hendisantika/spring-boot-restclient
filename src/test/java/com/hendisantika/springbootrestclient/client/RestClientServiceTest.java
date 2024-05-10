package com.hendisantika.springbootrestclient.client;

import com.hendisantika.springbootrestclient.dto.EmployeeDto;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-restclient
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 5/10/24
 * Time: 18:17
 * To change this template use File | Settings | File Templates.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@RequiredArgsConstructor
class RestClientServiceTest {
    private final RestClient restClient;

    public RestClientServiceTest() {
        restClient = RestClient.builder()
                .baseUrl("http://localhost:8080")
                .build();
    }

    @Order(1)
    @Test
    public void createEmployee() {
        EmployeeDto newEmployee = new EmployeeDto(null, "admin", "admin", "admin123@gmail.com");

        EmployeeDto savedEmployee = restClient.post()
                .uri("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .body(newEmployee)
                .retrieve()
                .body(EmployeeDto.class);

        System.out.println(savedEmployee.toString());
    }

    @Order(2)
    @Test
    public void getEmployeeById() {
        Long employeeId = 1L;

        EmployeeDto employeeDto = restClient.get()
                .uri("/api/employees/{id}", employeeId)
                .retrieve()
                .body(EmployeeDto.class);

        System.out.println(employeeDto);
    }

    @Order(3)
    @Test
    public void updateEmployee() {
        Long employeeId = 1L;

        EmployeeDto updatedEmployee = new EmployeeDto();
        updatedEmployee.setFirstName("Ramesh");
        updatedEmployee.setLastName("Fadatare");
        updatedEmployee.setEmail("ramesh@gmail.com");

        EmployeeDto result = restClient.put()
                .uri("/api/employees/{id}", employeeId)
                .contentType(MediaType.APPLICATION_JSON)
                .body(updatedEmployee)
                .retrieve()
                .body(EmployeeDto.class);

        System.out.println(result.toString());
    }

    @Order(4)
    @Test
    public void findAll() {
        List<EmployeeDto> listOfEmployees = restClient.get()
                .uri("/api/employees")
                .retrieve()
                .body(new ParameterizedTypeReference<List<EmployeeDto>>() {
                });

        listOfEmployees.forEach(employeeDto -> {
            System.out.println(employeeDto.toString());
        });
    }

    @Order(5)
    @Test
    public void deleteEmployee() {
        Long employeeId = 1L;

        String response = restClient.delete()
                .uri("/api/employees/{id}", employeeId)
                .retrieve()
                .body(String.class);

        System.out.println(response);
    }

    @Test
    public void exceptionHandlingClientErrorDemo() {
        HttpClientErrorException thrown = Assertions.assertThrows(HttpClientErrorException.class,
                () -> {

                    EmployeeDto employee = restClient.get()
                            .uri("/employees/404")
                            .accept(MediaType.APPLICATION_JSON)
                            .retrieve()
                            .body(EmployeeDto.class);
                });

        Assertions.assertEquals(404, thrown.getStatusCode().value());
    }
}
