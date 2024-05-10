package com.hendisantika.springbootrestclient.service;

import com.hendisantika.springbootrestclient.dto.EmployeeDto;
import com.hendisantika.springbootrestclient.model.Employee;
import com.hendisantika.springbootrestclient.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-restclient
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 5/10/24
 * Time: 18:07
 * To change this template use File | Settings | File Templates.
 */
@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeConverter.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeConverter.mapToEmployeeDto(savedEmployee);
    }
}
