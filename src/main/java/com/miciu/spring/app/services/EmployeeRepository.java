package com.miciu.spring.app.services;

import com.miciu.spring.app.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    @Query("Select e FROM Employee e WHERE e.age > :minAge")
    List<Employee> findOlderThan(int minAge);

    //Employee save(Employee employee);
}
