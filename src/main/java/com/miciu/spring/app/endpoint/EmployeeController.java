package com.miciu.spring.app.endpoint;

import com.miciu.spring.app.model.Employee;
import com.miciu.spring.app.services.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * TODO: controller
     * TODO: validacja na testy
     * TODO: testy mają działać
     * TODO: autowire service do kontrolera
     * NEXT: DELETE OBJECT
     *
     * @param ex
     * @return
     */

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @GetMapping("/employee")
    List<Employee> getAllEmployee() {
        try {
            return employeeService.readEmployeesFromJson();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    @PostMapping("/employee")
    void addEmployee(@Valid @RequestBody Employee employee) {
        /**
         * In case of issue with validation bad request will be automatically thrown by  request
         */
        log.info("added new employee : " + employee);
    }

}
