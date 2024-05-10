package com.hendisantika.springbootrestclient.repository;

import com.hendisantika.springbootrestclient.model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-restclient
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 5/10/24
 * Time: 18:01
 * To change this template use File | Settings | File Templates.
 */
@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EmployeeJdbcRepository {

    private final JdbcClient jdbcClient;

    public List<Employee> findAll() {
        String sql = "SELECT * FROM employees";
        return jdbcClient.sql(sql).query(Employee.class).list();
    }

    public Optional<Employee> findById(Long id) {
        String sql = "SELECT * FROM employees WHERE id = :id";
        return jdbcClient.sql(sql).param("id", id).query(Employee.class).optional();
    }
}
