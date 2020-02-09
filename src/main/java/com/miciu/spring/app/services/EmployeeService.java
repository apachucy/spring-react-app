package com.miciu.spring.app.services;

import com.miciu.spring.app.model.Employee;

import java.util.List;

public interface EmployeeService {

  List<Employee> readEmployees();
  
  void addEmployee(Employee employee);
}
