package com.example.employeemodel.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "companies")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comp_seq")
    @SequenceGenerator(name = "comp_seq", sequenceName = "comp_sequence", allocationSize = 1)
    private Long id;

    private String name;
    private String address;
    private String industry;
    private String website;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Department> departments;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employee;
}
