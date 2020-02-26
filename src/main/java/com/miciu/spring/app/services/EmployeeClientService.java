package com.miciu.spring.app.services;

import com.miciu.spring.app.model.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import java.util.List;

@Service
public class EmployeeClientService {
    private final RestOperations restTemplate;


    //TODO: understand this approach

    //url for retrieving Employee list
    @Value("${oauth2.client.employee.url}")
    private String clientUrl;

    @Autowired
    public EmployeeClientService(RestOperations restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<EmployeeDto> readEmployees() {
        //TODO call client API to get list of employees

        //  restTemplate.getForEntity(clientUrl, EmployeeDto.class);
        ResponseEntity<List<EmployeeDto>> exchange = restTemplate.exchange(clientUrl, HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<List<EmployeeDto>>() {
        });
        //return Collections.emptyList();
        return exchange.getBody();
    }
}
