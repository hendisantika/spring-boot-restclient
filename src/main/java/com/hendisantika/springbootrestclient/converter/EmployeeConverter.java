package com.hendisantika.springbootrestclient.converter;

import com.hendisantika.springbootrestclient.dto.EmployeeDto;
import com.hendisantika.springbootrestclient.model.Employee2;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-restclient
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 5/10/24
 * Time: 18:09
 * To change this template use File | Settings | File Templates.
 */
public class EmployeeConverter {
    // convert Employee JPA entity to EmployeeDto
    // convert EmployeeDto to Employee JPA entity
    public static Employee2 mapToEmployee(EmployeeDto employeeDto) {
        Employee2 employee = new Employee2(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail()
        );
        return employee;
    }

    // convert Employee JPA entity to EmployeeDto
    // convert EmployeeDto to Employee JPA entity
    public static EmployeeDto mapToEmployeeDto(Employee2 employee) {
        EmployeeDto employeeDto = new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()
        );
        return employeeDto;
    }
}
