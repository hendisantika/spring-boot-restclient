package com.hendisantika.springbootrestclient;

import com.hendisantika.springbootrestclient.model.Employee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermissions;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class SpringBootRestclientApplication implements CommandLineRunner {
    private final WebClient webClient;
    public static void main(String[] args) {
        SpringApplication.run(SpringBootRestclientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        FileAttribute<?>[] attributes = {
                PosixFilePermissions.asFileAttribute(PosixFilePermissions.fromString("rw-r--r--"))
        };
        Path tempFile = Files.createTempFile(Files.createTempDirectory("temp-dir"), "testData-", ".txt", attributes);

        log.info(tempFile.toAbsolutePath().toString());

        webClient.post()
                .uri("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new Employee(180, "Naruto", "Active"))
                .retrieve()
                .toBodilessEntity()
                .subscribe(responseEntity -> {
                    if (responseEntity.getStatusCode().is2xxSuccessful()) {

                        int statusCode = responseEntity.getStatusCode().value();
                        String locationURI = responseEntity.getHeaders().getLocation().toString();

                        log.info("Status: " + statusCode);
                        log.info("Location URI: " + locationURI);
                    }
                });

    /*webClient.post()
        .uri("/create")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(new Employee(180, "Naruto", "Active"))
        .retrieve()
        .onStatus(HttpStatusCode::is4xxClientError,
            clientResponse -> {
              System.err.println("Client error: " + clientResponse.statusCode().value());
              return Mono.error(new RuntimeException("Client error"));
            })
        .onStatus(HttpStatusCode::is5xxServerError,
            clientResponse -> {
              System.err.println("Server error: " + clientResponse.statusCode().value());
              return Mono.error(new RuntimeException("Server error"));
            })
        .onStatus(HttpStatusCode::isError,
            clientResponse -> {
              System.err.println("Other error: " + clientResponse.statusCode().value());
              return Mono.error(new RuntimeException("Unknown error"));
            })
        .toBodilessEntity()
        .subscribe(responseEntity -> {
          if (responseEntity.getStatusCode().is2xxSuccessful()) {

            int statusCode = responseEntity.getStatusCode().value();
            String locationURI = responseEntity.getHeaders().getLocation().toString();

            System.out.println("Status: " + statusCode);
            System.out.println("Location URI: " + locationURI);
          }
        });

    //Create a Resource and Get Status and Location Header
    Employee newEmployee = new Employee(180, "Naruto", "Active");
    webClient.post()
        .uri("http://localhost:3000/employees")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(newEmployee)
        .retrieve()
        .toBodilessEntity()
        .subscribe(responseEntity -> {
          if (responseEntity.getStatusCode().is2xxSuccessful()) {

            int statusCode = responseEntity.getStatusCode().value();
            String locationURI = responseEntity.getHeaders().getLocation().toString();

            System.out.println("Status: " + statusCode);
            System.out.println("Location URI: " + locationURI);
          }
        });


    //Create a Resource and Get Status, Location Header and Body

    newEmployee = new Employee(190, "Naruto", "Active");
    webClient.post()
        .uri("http://localhost:3000/employees")
        .body(Mono.just(newEmployee), Employee.class)
        .retrieve()
        .onStatus(HttpStatusCode::is4xxClientError, response -> {
          return Mono.just(new ApplicationException("Bad Request"));
        })
        .onStatus(HttpStatusCode::is5xxServerError, response -> {
          return Mono.just(new ApplicationException("Server Error"));
        })
        .toEntity(Employee.class)
        .subscribe(responseEntity -> {
          System.out.println("Status: " + responseEntity.getStatusCode().value());
          System.out.println("Location URI: " + responseEntity.getHeaders().getLocation().toString());
          System.out.println("Created New Employee : " + responseEntity.getBody());
        });

    //Submit Form
    webClient.post()
        .uri("http://localhost:3000/employees")
        .body(BodyInserters.fromFormData("id", "1900")
            .with("name", "test name")
            .with("status", "active"))
        .retrieve()
        .onStatus(HttpStatusCode::is4xxClientError, response -> {
          return Mono.just(new ApplicationException("Bad Request"));
        })
        .onStatus(HttpStatusCode::is5xxServerError, response -> {
          return Mono.just(new ApplicationException("Server Error"));
        })
        .toEntity(Employee.class)
        .subscribe(responseEntity -> {
          System.out.println("Status: " + responseEntity.getStatusCode().value());
          System.out.println("Location URI: " + responseEntity.getHeaders().getLocation().toString());
          System.out.println("Created New Employee : " + responseEntity.getBody());
        });

    //With Multipart
    MultipartBodyBuilder builder = new MultipartBodyBuilder();

    builder.part("file", new FileSystemResource("c:/temp/file-name.txt"));
    builder.part("id", "190001", MediaType.TEXT_PLAIN);
    builder.part("name", "Naruto", MediaType.TEXT_PLAIN);
    builder.part("status", "active", MediaType.TEXT_PLAIN);

    webClient.post()
        .uri("http://localhost:3000/employees")
        .contentType(MediaType.MULTIPART_FORM_DATA)
        .body(BodyInserters.fromMultipartData(builder.build()))
        .retrieve()
        .toEntity(Employee.class)
        .doOnError(WriteTimeoutException.class, ex -> {
          System.err.println("WriteTimeout");
        })
        .subscribe(responseEntity -> {
          System.out.println("Status: " + responseEntity.getStatusCode().value());
          System.out.println("Location URI: " + responseEntity.getHeaders().getLocation().toString());
          System.out.println("Created New Employee : " + responseEntity.getBody());
        });*/

    }
}
