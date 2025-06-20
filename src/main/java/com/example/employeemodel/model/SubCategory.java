package com.example.employeemodel.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "sub_categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubCategory{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subcat_seq")
    @SequenceGenerator(name = "subcat_seq", sequenceName = "subcat_sequence", allocationSize = 1)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "subCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employee;

}
