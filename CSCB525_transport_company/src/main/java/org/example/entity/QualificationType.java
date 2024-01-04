package org.example.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "qualification_type")
public class QualificationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "qualification_type_name")
    private String name;

    @ManyToMany(mappedBy = "qualificationTypeSet")
    private Set<Employee> employeeSet = new HashSet<>();

    public QualificationType(String name) {
        this.name = name;
    }

    public QualificationType(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "QualificationType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
    //TODO: make the connections
}
