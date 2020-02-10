package com.miciu.spring.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miciu.spring.app.services.DefaultEmployeeService;
import com.miciu.spring.app.services.EmployeeService;
import com.miciu.spring.app.services.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

@Configuration
public class EmployeeConfiguration {

    /**
     * if both @Beans are "equal"
     * Consider marking one of the beans as @Primary, updating the consumer to accept multiple beans, or using @Qualifier to identify the bean that should be consumed
     */

    private final Environment environment;
    private final ObjectMapper mapper;

    @Autowired
    public EmployeeConfiguration(Environment environment, ObjectMapper mapper) {
        this.environment = environment;
        this.mapper = mapper;
    }

    @Bean
    @Profile("local")
    EmployeeService defaultEmployeeService() {
        return new DefaultEmployeeService(environment);
    }

    @Bean
    @Profile("prod")
    EmployeeService employeeServiceImpl() {
        return new EmployeeServiceImpl(mapper);
    }
}
