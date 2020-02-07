package com.miciu.spring.app.services;

import com.miciu.spring.app.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@PropertySource("classpath:application.properties")
@Service
@Profile("local")
//@Qualifier("DefaultImpl")

public class DefaultEmployeeService implements EmployeeService {
    private String firstName;
    private String lastName;
    private int age;

    private final Environment environment;

    @Autowired
    public DefaultEmployeeService(Environment environment) {
        this.environment = environment;
        this.firstName = environment.getProperty("employee.first.name");
        this.lastName = environment.getProperty("employee.last.name");
        String employeeAge = environment.getProperty("employee.age").trim();
        this.age = Integer.parseInt(employeeAge);
    }

    @Override
    public List<Employee> readEmployees() {
        Employee employee = new Employee(firstName, lastName, age);
        return Arrays.asList(employee);
    }
}
