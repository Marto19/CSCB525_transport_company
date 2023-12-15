package org.example.entity;

import org.example.enums.QualificationType;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "qualification_type")
    private QualificationType qualificationType;
    @Column(name = "name")
    private String name;
    @ManyToOne                                  //n:1 - Employee:TransportCompany
    private TransportCompany transportCompany;


}
