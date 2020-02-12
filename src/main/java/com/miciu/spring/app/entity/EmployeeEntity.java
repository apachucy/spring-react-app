package com.miciu.spring.app.entity;

import com.miciu.spring.app.model.Profession;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@ToString
@Entity
@Table(name = "employee")
public class EmployeeEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private int birthYear;

    @Enumerated(EnumType.STRING)
    private Profession profession;

    public EmployeeEntity(String firstName, String lastName, int birthYear, Profession profession) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.profession = profession;
    }
}
