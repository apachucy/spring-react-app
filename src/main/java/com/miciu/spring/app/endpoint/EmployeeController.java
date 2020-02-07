package com.miciu.spring.app.endpoint;

import com.miciu.spring.app.services.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.miciu.spring.app.model.Employee;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(//@Qualifier("ServiceImpl")
                                          EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "employee", method = RequestMethod.GET)
    public List<Employee> getEmployees() {
        try {
            List<Employee> employees = employeeService.readEmployees();
            return employees;

        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @RequestMapping(value = "employee", method = RequestMethod.POST)
    public ResponseEntity addEmployee(@RequestBody @Valid Employee employee) {
        log.info("Successfully added: {}", employee);
        return ResponseEntity.ok().build();
    }

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
}
