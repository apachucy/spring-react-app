package com.miciu.spring.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@ToString
public class Employee {
    @NotNull(message = "firstName is mandatory")
    @Size(min = 1, message = "firstName is mandatory")
    private String firstName;
    @NotNull(message = "lastName is mandatory")
    @Size(min = 1, message = "lastName is mandatory")
    private String lastName;
    @Min(value = 18, message = "the minimum age is 18 years")
    private int age;
}
